package org.toxicgames.memology.common.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Optional;

@AllArgsConstructor
@Builder
@Jacksonized
public class TelegramUserRegistrationData implements UserRegistrationData {

    private final long telegramId;

    @Getter
    private final String name;

    @Override
    public Optional<Long> getTelegramId() {
        return Optional.of(telegramId);
    }

}

