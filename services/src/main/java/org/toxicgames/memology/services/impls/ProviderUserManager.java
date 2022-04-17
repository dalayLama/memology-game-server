package org.toxicgames.memology.services.impls;

import org.springframework.stereotype.Service;
import org.toxicgames.memology.dao.entities.User;
import org.toxicgames.memology.dao.services.UserDaoService;
import org.toxicgames.memology.dao.services.UserUpdateData;
import org.toxicgames.memology.dto.UserDTO;
import org.toxicgames.memology.dto.components.UserDTOMapper;
import org.toxicgames.memology.services.UserManager;
import org.toxicgames.memology.services.UserRegistrationData;
import org.toxicgames.memology.services.UserRegistrationProvider;
import org.toxicgames.memology.services.exceptions.RegistrationUserException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProviderUserManager implements UserManager {

    private final List<UserRegistrationProvider> providers;

    private final UserDaoService daoService;

    private final UserDTOMapper dtoMapper;

    public ProviderUserManager(UserDaoService daoService,
                               UserDTOMapper dtoMapper,
                               UserRegistrationProvider... providers) {
        this.daoService = daoService;
        this.dtoMapper = dtoMapper;
        this.providers = Arrays.asList(providers);
    }

    @Override
    public UserDTO register(UserRegistrationData registrationData) {
        List<UserRegistrationProvider> result = providers.stream()
                .filter(provider -> provider.supports(registrationData))
                .collect(Collectors.toList());
        if (result.size() != 1) {
            throw new RegistrationUserException();
        }
        User newUser = result.get(0).register(registrationData);
        return dtoMapper.convert(newUser);
    }

    @Override
    public UserDTO update(UserUpdateData updateData) {
        User result = daoService.update(updateData);
        return dtoMapper.convert(result);
    }

    @Override
    public void deleteById(UUID id) {
        daoService.deleteById(id);
    }

}
