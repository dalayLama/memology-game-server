package org.toxicgames.memology.dao.services.impls;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.toxicgames.memology.dao.entities.User;
import org.toxicgames.memology.dao.repositories.UserRepository;
import org.toxicgames.memology.dao.services.UserDaoService;
import org.toxicgames.memology.dao.services.UserUpdateData;

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
    public Optional<User> findByTelegramId(long id) {
        return userRepository.findByTelegramId(id);
    }

    @Override
    @Transactional
    public User create(User user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(UserUpdateData updateData) {
        User user = findById(updateData.getUserId()).orElseThrow(EntityNotFoundException::new);
        user.setName(updateData.getName());
        return userRepository.save(user);
    }

    @Override
    public void deleteById(UUID id) {
        if (!userRepository.exists(id)) {
            throw new EntityNotFoundException();
        }
        userRepository.deleteById(id);
    }
}
