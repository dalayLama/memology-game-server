package org.toxicgames.memology.services;

import org.toxicgames.memology.dao.services.UserUpdateData;
import org.toxicgames.memology.dto.UserDTO;

import java.util.UUID;

public interface UserManager {

    UserDTO register(UserRegistrationData registrationData);

    UserDTO update(UserUpdateData updateData);

    void deleteById(UUID id);

}
