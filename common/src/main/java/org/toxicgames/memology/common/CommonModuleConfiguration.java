package org.toxicgames.memology.common;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.toxicgames.memology.common.components.AppMessageSource;
import org.toxicgames.memology.common.components.errors.ErrorResponseGenerator;
import org.toxicgames.memology.common.components.errors.ErrorResponseProvider;
import org.toxicgames.memology.common.components.errors.impls.ProviderErrorResponseGenerator;
import org.toxicgames.memology.common.components.impls.DefaultAppMessageSource;
import org.toxicgames.memology.common.constants.BeanNames;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

@Configuration
public class CommonModuleConfiguration {

    @Bean
    public ErrorResponseGenerator errorResponseGenerator(@Qualifier(BeanNames.DEFAULT_ERROR_RESPONSE_PROVIDER) ErrorResponseProvider defaultProvider,
                                                         ErrorResponseProvider... providers) {
        ErrorResponseProvider[] additionalProviders = Arrays.stream(providers)
                .filter(p -> p != defaultProvider)
                .toArray(ErrorResponseProvider[]::new);

        return new ProviderErrorResponseGenerator(defaultProvider, additionalProviders);
    }

    @Bean
    public Locale defaultLocale() {
        return new Locale("ru", "RU");
    }

    @Bean
    public AppMessageSource appMessageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasename("classpath:messages/messages");
        ms.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return new DefaultAppMessageSource(ms, defaultLocale());
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
