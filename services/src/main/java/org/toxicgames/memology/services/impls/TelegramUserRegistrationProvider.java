package org.toxicgames.memology.services.impls;

import org.springframework.stereotype.Component;
import org.toxicgames.memology.dao.entities.TelegramData;
import org.toxicgames.memology.dao.entities.User;
import org.toxicgames.memology.dao.services.UserDaoService;
import org.toxicgames.memology.services.TelegramUserRegistrationData;
import org.toxicgames.memology.services.UserRegistrationData;
import org.toxicgames.memology.services.UserRegistrationProvider;

@Component
public class TelegramUserRegistrationProvider implements UserRegistrationProvider {

    private final UserDaoService daoService;

    public TelegramUserRegistrationProvider(UserDaoService daoService) {
        this.daoService = daoService;
    }

    @Override
    public User register(UserRegistrationData registrationData) {
        TelegramUserRegistrationData telegramData = (TelegramUserRegistrationData) registrationData;
        User user = new User(null, telegramData.getName(), null);
        TelegramData tg = new TelegramData(telegramData.getTelegramId(), user);
        user.setTelegramData(tg);
        return daoService.create(user);
    }

    @Override
    public boolean supports(UserRegistrationData registrationData) {
        return registrationData.getClass().isAssignableFrom(TelegramUserRegistrationData.class);
    }

}
