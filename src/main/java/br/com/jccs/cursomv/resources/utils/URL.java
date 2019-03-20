package br.com.jccs.cursomv.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URL {

    //método para decodificar o parametro nome
    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
           return "";
        }
    }
    
    
    public static List<Integer> decodeIntList(String s) {
        String [] vet = s.split(",");
        
        List<Integer> list = new ArrayList<>();
        
        for (int i=0 ; i<vet.length;i++) {
            list.add(Integer.parseInt(vet[i]));
        }
        
        return list;
    }
    
}
