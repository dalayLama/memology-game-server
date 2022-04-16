package org.toxicgames.memology.dao.repositories.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.toxicgames.memology.dao.entities.TelegramData;
import org.toxicgames.memology.dao.entities.User;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JpaUserRepositoryTest {

    @Autowired
    private JpaUserRepository userRepository;

    @Test
    void createTelegramUser() {
        TelegramData telegramData = new TelegramData(23L, null);
        User user = User.builder()
                .id(UUID.randomUUID())
                .name("test")
                .telegramData(telegramData)
                .build();
        telegramData.setUser(user);

        userRepository.saveAndFlush(user);
        Optional<User> byTelegramId = userRepository.findByTelegramId(telegramData.getId());

        assertThat(byTelegramId)
                .isPresent().get()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo(user.getId());
                    assertThat(u.getName()).isEqualTo(user.getName());
                    assertThat(u.getTelegramData()).isNotNull();
                    assertThat(u.getTelegramData().getId()).isEqualTo(telegramData.getId());
                });
    }

}