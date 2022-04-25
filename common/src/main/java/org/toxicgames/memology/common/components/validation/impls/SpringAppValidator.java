package org.toxicgames.memology.common.components.validation.impls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.toxicgames.memology.common.components.validation.AppValidator;
import org.toxicgames.memology.common.exceptions.ValidationException;

import java.util.List;

@Component
class SpringAppValidator implements AppValidator {

    private final static Logger LOG = LoggerFactory.getLogger(SpringAppValidator.class);

    private final List<Validator> validators;

    public SpringAppValidator(Validator... validators) {
        this.validators = List.of(validators);
    }

    @Override
    public void validate(Object object, String objectName) throws ValidationException {
        Validator[] supportedValidators = validators.stream()
                .filter(v -> v.supports(object.getClass()))
                .toArray(Validator[]::new);
        if (supportedValidators.length == 0) {
            LOG.warn("Appropriate validators for \"{}\" hasn't been found", object.getClass());
            return;
        }
        DataBinder dataBinder = new DataBinder(object, objectName);
        dataBinder.addValidators(supportedValidators);
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();
        if (!bindingResult.getAllErrors().isEmpty()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
    }

}
