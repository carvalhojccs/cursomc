package br.com.jccs.cursomv.services.exceptions;

public class ObjectNotFouondException extends RuntimeException{
    
    public ObjectNotFouondException(String msg){
        super(msg);
    }
    
    public ObjectNotFouondException(String msg, Throwable cause){
        super(msg, cause);
    }
}
