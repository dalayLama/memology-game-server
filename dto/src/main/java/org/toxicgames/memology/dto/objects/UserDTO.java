package org.toxicgames.memology.dto.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class UserDTO {

    private final UUID id;

    private final Long telegramId;

    private final String name;

}
