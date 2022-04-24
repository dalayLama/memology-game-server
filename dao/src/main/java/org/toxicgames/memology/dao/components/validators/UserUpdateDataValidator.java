package org.toxicgames.memology.dao.components.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.toxicgames.memology.common.constants.ValidationErrorCodes;
import org.toxicgames.memology.common.objects.UserUpdateData;

@Component
public class UserUpdateDataValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserUpdateData.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", ValidationErrorCodes.USER_NAME_IS_EMPTY);
    }

}
