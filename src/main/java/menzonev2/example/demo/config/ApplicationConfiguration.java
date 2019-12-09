package menzonev2.example.demo.config;

import menzonev2.example.demo.services.URLTrimService;
import menzonev2.example.demo.services.impl.URLTrimServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
@Configuration
public class ApplicationConfiguration {


    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

    @Bean
    public URLTrimService trimService(){

        return new URLTrimServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
