package org.toxicgames.memology.dao.services.impls;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.toxicgames.memology.common.components.validation.AppValidator;
import org.toxicgames.memology.common.objects.UserRegistrationData;
import org.toxicgames.memology.common.objects.UserUpdateData;
import org.toxicgames.memology.dao.entities.User;
import org.toxicgames.memology.dao.services.UserDaoService;

import java.util.Optional;
import java.util.UUID;

@Service
@Primary
public class ValidUserDaoService implements UserDaoService {

    private final UserDaoService delegate;

    private final AppValidator validator;

    public ValidUserDaoService(UserDaoService delegate, AppValidator validator) {
        this.delegate = delegate;
        this.validator = validator;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return delegate.findById(id);
    }

    @Override
    public Optional<User> findByTelegramId(long id) {
        return delegate.findByTelegramId(id);
    }

    @Override
    public User create(UserRegistrationData registrationData) {
        validator.validate(registrationData, "user");
        return delegate.create(registrationData);
    }

    @Override
    public User update(UserUpdateData updateData) {
        validator.validate(updateData, "user");
        return delegate.update(updateData);
    }

    @Override
    public void deleteById(UUID id) {
        delegate.deleteById(id);
    }

    @Override
    public boolean existByTelegramId(long telegramId) {
        return delegate.existByTelegramId(telegramId);
    }
}
