package org.toxicgames.memology.dao.repositories;

import org.toxicgames.memology.dao.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {

    Optional<User> findByTelegramId(long telegramId);

}
