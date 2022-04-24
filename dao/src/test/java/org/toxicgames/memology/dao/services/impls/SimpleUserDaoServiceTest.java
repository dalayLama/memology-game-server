package org.toxicgames.memology.dao.services.impls;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.toxicgames.memology.common.objects.SimpleUserRegistrationData;
import org.toxicgames.memology.common.objects.TelegramUserRegistrationData;
import org.toxicgames.memology.dao.entities.User;
import org.toxicgames.memology.dao.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class SimpleUserDaoServiceTest {

    private final UserRepository repository = spy(UserRepository.class);

    private final SimpleUserDaoService daoService = new SimpleUserDaoService(repository);

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    @DisplayName("should not create a telegram data when the data does not have a telegram id")
    void shouldNotCreateATelegramDataWhenTheDataDoesNotHaveATelegramId() {
        SimpleUserRegistrationData regData = new SimpleUserRegistrationData("name");
        daoService.create(regData);

        verify(repository).save(userArgumentCaptor.capture());
        User user = userArgumentCaptor.getValue();
        assertThat(user.getId()).isNotNull();
        assertThat(user.getName()).isEqualTo(regData.getName());
        assertThat(user.getTelegramData()).isNull();
    }

    @Test
    @DisplayName("should create a telegram data when the data has a telegram id")
    void shouldCreateATelegramDataWhenTheDataHasATelegramId() {
        final long telegramId = 1L;
        TelegramUserRegistrationData regData = new TelegramUserRegistrationData(telegramId, "name");
        daoService.create(regData);

        verify(repository).save(userArgumentCaptor.capture());
        User user = userArgumentCaptor.getValue();
        assertThat(user.getId()).isNotNull();
        assertThat(user.getName()).isEqualTo(regData.getName());
        assertThat(user.getTelegramData()).isNotNull();
        assertThat(user.getTelegramData().getId()).isEqualTo(telegramId);
        assertThat(user.getTelegramData().getUser()).isSameAs(user);
    }

}