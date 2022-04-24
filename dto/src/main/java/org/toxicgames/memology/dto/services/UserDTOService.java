package org.toxicgames.memology.dto.services;

import org.toxicgames.memology.common.objects.UserRegistrationData;
import org.toxicgames.memology.common.objects.UserUpdateData;
import org.toxicgames.memology.dto.objects.UserDTO;

public interface UserDTOService {

    UserDTO create(UserRegistrationData registrationData);

    UserDTO update(UserUpdateData updateData);

}
