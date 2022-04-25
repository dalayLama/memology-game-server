package org.toxicgames.memology.dao.repositories.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.toxicgames.memology.dao.entities.User;
import org.toxicgames.memology.dao.repositories.UserRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends UserRepository, JpaRepository<User, UUID> {

    @Override
    @EntityGraph(attributePaths = {
            "telegramData"
    })
    @Query("select u from User u " +
            "left join u.telegramData td " +
            "where td.id = ?1")
    Optional<User> findByTelegramId(long telegramId);

    @Override
    @Query("select count(u) from User u left join u.telegramData td where td.id = ?1")
    boolean existsByTelegramId(long telegramId);

}
