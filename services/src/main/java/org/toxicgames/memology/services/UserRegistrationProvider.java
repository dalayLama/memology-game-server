package org.toxicgames.memology.services;

import org.toxicgames.memology.dao.entities.User;

public interface UserRegistrationProvider {

    User register(UserRegistrationData registrationData);

    boolean supports(UserRegistrationData registrationData);

}
