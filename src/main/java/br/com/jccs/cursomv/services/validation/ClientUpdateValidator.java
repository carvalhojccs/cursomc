package br.com.jccs.cursomv.services.validation;

import br.com.jccs.cursomv.domain.Cliente;
import br.com.jccs.cursomv.dto.ClienteDTO;
import br.com.jccs.cursomv.repositories.ClienteRepository;
import br.com.jccs.cursomv.resources.exceptions.FieldMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class ClientUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private ClienteRepository repo;
    
    @Override
    public void initialize(ClienteUpdate ann) {
    }
    
    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        
        //uriId recebe o id como String e é feito um parse para Integer
        Integer uriId = Integer.parseInt(map.get("id"));
        
        List<FieldMessage> list = new ArrayList<>();
        
        //verifica se o email já está cadastrado
        Cliente aux = repo.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existente"));
        }
        
        
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
    
}
