package br.com.jccs.cursomv.dto;

import br.com.jccs.cursomv.domain.Categoria;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public CategoriaDTO() {
    }
    
    public CategoriaDTO(Categoria obj){
        id = obj.getId();
        nome = obj.getNome();
    }
    
    //GET / SET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
