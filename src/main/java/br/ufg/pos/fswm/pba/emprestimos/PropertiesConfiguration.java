package br.ufg.pos.fswm.pba.emprestimos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Classe respons&aacute;vel por carregar arquivo properties de configura&ccedil;&otilde;es personalizadas.
 *
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
@Configuration
@PropertySource(value = {"classpath:emprestimo-api.properties"})
public class PropertiesConfiguration {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
