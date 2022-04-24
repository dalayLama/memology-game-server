package org.toxicgames.memology.dao.services;

import org.toxicgames.memology.common.objects.UserRegistrationData;
import org.toxicgames.memology.common.objects.UserUpdateData;
import org.toxicgames.memology.dao.entities.User;

import java.util.Optional;
import java.util.UUID;


public interface UserDaoService {

    Optional<User> findById(UUID id);

    Optional<User> findByTelegramId(long id);

    User create(UserRegistrationData registrationData);

    User update(UserUpdateData updateData);

    void deleteById(UUID id);

    boolean existByTelegramId(long telegramId);

}
