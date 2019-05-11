package br.com.jccs.cursomv.config;

import br.com.jccs.cursomv.services.DBService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    
    @Autowired
    private DBService dBService;
    
    @Bean
    public boolean instatiateDatabase() throws ParseException {
        dBService.instatiateTestDatabase();
        
        return true;
    }
}
