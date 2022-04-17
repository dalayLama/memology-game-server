package org.toxicgames.memology.dao.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class UserUpdateData {

    private final UUID userId;

    private final String name;

}
