package org.toxicgames.memology.dao.services.impls;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.toxicgames.memology.common.annotations.Valid;
import org.toxicgames.memology.common.objects.UserRegistrationData;
import org.toxicgames.memology.common.objects.UserUpdateData;
import org.toxicgames.memology.dao.entities.TelegramData;
import org.toxicgames.memology.dao.entities.User;
import org.toxicgames.memology.dao.repositories.UserRepository;
import org.toxicgames.memology.dao.services.UserDaoService;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class SimpleUserDaoService implements UserDaoService {

    private final UserRepository userRepository;

    public SimpleUserDaoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existByTelegramId(long telegramId) {
        return userRepository.existsByTelegramId(telegramId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByTelegramId(long id) {
        return userRepository.findByTelegramId(id);
    }

    @Override
    @Transactional
    public User create(@Valid(objectName = "user") UserRegistrationData registrationData) {
        User newUser = User.builder()
                .id(UUID.randomUUID())
                .name(registrationData.getName())
                .build();
        registrationData.getTelegramId().ifPresent(id -> {
            TelegramData telegramData = new TelegramData(id, newUser);
            newUser.setTelegramData(telegramData);
        });
        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public User update(@Valid(objectName = "user") UserUpdateData updateData) {
        User user = findById(updateData.getId()).orElseThrow(EntityNotFoundException::new);
        user.setName(updateData.getName());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        userRepository.deleteById(id);
    }

}
