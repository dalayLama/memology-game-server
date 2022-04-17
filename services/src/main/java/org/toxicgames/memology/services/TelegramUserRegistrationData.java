package org.toxicgames.memology.services;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TelegramUserRegistrationData implements UserRegistrationData {

    private final long telegramId;

    private final String name;

}
