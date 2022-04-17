package org.toxicgames.memology.dao.services;

import org.springframework.transaction.annotation.Transactional;
import org.toxicgames.memology.dao.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserDaoService {

    Optional<User> findById(UUID id);

    @Transactional(readOnly = true)
    Optional<User> findByTelegramId(long id);

    User create(User user);

    User update(UserUpdateData updateData);

    void deleteById(UUID id);

}
