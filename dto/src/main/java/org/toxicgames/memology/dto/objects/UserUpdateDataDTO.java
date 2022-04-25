package org.toxicgames.memology.dto.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.toxicgames.memology.common.objects.UserUpdateData;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
@Jacksonized
public class UserUpdateDataDTO implements UserUpdateData {

    private final UUID id;

    private final String name;

}
