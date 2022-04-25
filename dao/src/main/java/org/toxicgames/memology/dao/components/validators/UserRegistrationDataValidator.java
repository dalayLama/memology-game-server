package org.toxicgames.memology.dao.components.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.toxicgames.memology.common.constants.ValidationErrorCodes;
import org.toxicgames.memology.common.objects.UserRegistrationData;
import org.toxicgames.memology.dao.repositories.UserRepository;

@Component
public class UserRegistrationDataValidator implements Validator {

    private final UserRepository repository;

    public UserRegistrationDataValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistrationData.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegistrationData data = (UserRegistrationData) target;
        data.getTelegramId().ifPresent(id -> {
            if (repository.existsByTelegramId(id)) {
                errors.reject(ValidationErrorCodes.TELEGRAM_USER_ALREADY_EXISTS, new Object[] {id}, null);
            }
        });
        ValidationUtils.rejectIfEmpty(errors, "name", ValidationErrorCodes.USER_NAME_IS_EMPTY);
    }

}
