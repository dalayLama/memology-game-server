package org.toxicgames.memology.dao.services.impls;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.toxicgames.memology.common.spring.ValidAnnotationBeanPostProcessor;

@Configuration
@ComponentScan(basePackages = {
        "org.toxicgames.memology.common.components.validation",
        "org.toxicgames.memology.dao.services",
        "org.toxicgames.memology.dao.components.validators"
})
public class PostProcessorConfiguration {

    @Bean
    public ValidAnnotationBeanPostProcessor validAnnotationBeanPostProcessor(ApplicationContext context) {
        return new ValidAnnotationBeanPostProcessor(context);
    }

}
