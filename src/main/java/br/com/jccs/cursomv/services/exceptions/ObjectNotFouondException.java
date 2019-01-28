package br.com.jccs.cursomv.services.exceptions;

public class ObjectNotFouondException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public ObjectNotFouondException(String msg){
        super(msg);
    }
    
    public ObjectNotFouondException(String msg, Throwable cause){
        super(msg, cause);
    }
}
