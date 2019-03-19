package br.com.jccs.cursomv.services.validation;

import br.com.jccs.cursomv.domain.Cliente;
import br.com.jccs.cursomv.domain.enums.TipoCliente;
import br.com.jccs.cursomv.dto.ClienteNewDTO;
import br.com.jccs.cursomv.repositories.ClienteRepository;
import br.com.jccs.cursomv.resources.exceptions.FieldMessage;
import br.com.jccs.cursomv.services.validation.utils.BR;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientIsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
    
    @Autowired
    private ClienteRepository repo;
    
    @Override
    public void initialize(ClienteInsert ann) {
    }
    
    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        
        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
        }
        
        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
        }
        
        //verifica se o email já está cadastrado
        Cliente aux = repo.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }
        
        
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
    
}
