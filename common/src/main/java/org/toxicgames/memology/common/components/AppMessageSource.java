package org.toxicgames.memology.common.components;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

public interface AppMessageSource extends MessageSource {

    String getMessage(String code, Object[] args, String defaultMessage);

    String getMessage(String code, Object[] args) throws NoSuchMessageException;

    String getMessage(MessageSourceResolvable resolvable) throws NoSuchMessageException;

}
