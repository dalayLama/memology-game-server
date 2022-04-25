package org.toxicgames.memology.common.components.impls;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;
import org.toxicgames.memology.common.components.AppMessageSource;

import java.util.Locale;

public class DefaultAppMessageSource implements AppMessageSource {

    private final MessageSource messageSource;

    private final Locale defaultLocale;

    public DefaultAppMessageSource(MessageSource messageSource, Locale defaultLocale) {
        this.messageSource = messageSource;
        this.defaultLocale = defaultLocale;
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage) {
        return getMessage(code, args, defaultMessage, defaultLocale);
    }

    @Override
    public String getMessage(String code, Object[] args) throws NoSuchMessageException {
        return getMessage(code, args, defaultLocale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable) throws NoSuchMessageException {
        return getMessage(resolvable, defaultLocale);
    }

    @Override
    @Nullable
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(code, args, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(resolvable, locale);
    }
}
