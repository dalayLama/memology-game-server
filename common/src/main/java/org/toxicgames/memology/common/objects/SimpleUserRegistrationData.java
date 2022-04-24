package org.toxicgames.memology.common.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Optional;

@AllArgsConstructor
@Builder
@Getter
@Jacksonized
public class SimpleUserRegistrationData implements UserRegistrationData {

    private final String name;

    @Override
    public Optional<Long> getTelegramId() {
        return Optional.empty();
    }

}
