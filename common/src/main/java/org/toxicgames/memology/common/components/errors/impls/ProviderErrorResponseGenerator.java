package org.toxicgames.memology.common.components.errors.impls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.toxicgames.memology.common.components.errors.ErrorResponseGenerator;
import org.toxicgames.memology.common.components.errors.ErrorResponseProvider;
import org.toxicgames.memology.common.components.errors.ErrorResponse;

import java.util.Arrays;
import java.util.List;

public class ProviderErrorResponseGenerator implements ErrorResponseGenerator {

    private final static Logger LOG = LoggerFactory.getLogger(ProviderErrorResponseGenerator.class);

    private final ErrorResponseProvider defaultProvider;

    private final List<ErrorResponseProvider> providers;

    public ProviderErrorResponseGenerator(ErrorResponseProvider defaultProvider,
                                          ErrorResponseProvider... providers) {
        this.defaultProvider = defaultProvider;
        this.providers = Arrays.asList(providers);
    }

    @Override
    public ErrorResponse generate(Throwable throwable) {
        return this.providers.stream()
                .filter(p -> p.supports(throwable))
                .findFirst()
                .orElseGet(() -> {
                    LOG.warn("An error response provider hasn't been found for {}. " +
                            "The default provider({}) will be used", throwable.getClass(), defaultProvider.getClass());
                    return defaultProvider;
                })
                .getErrorResponse(throwable);
    }

}
