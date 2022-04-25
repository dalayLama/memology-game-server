package org.toxicgames.memology.common.objects;

import java.util.Optional;

public interface UserRegistrationData {

    String getName();

    Optional<Long> getTelegramId();

}
