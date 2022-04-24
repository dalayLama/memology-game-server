package org.toxicgames.memology.dto.services.impls;

import org.springframework.stereotype.Service;
import org.toxicgames.memology.common.objects.UserRegistrationData;
import org.toxicgames.memology.common.objects.UserUpdateData;
import org.toxicgames.memology.dao.entities.User;
import org.toxicgames.memology.dao.services.UserDaoService;
import org.toxicgames.memology.dto.components.UserDTOMapper;
import org.toxicgames.memology.dto.objects.UserDTO;
import org.toxicgames.memology.dto.services.UserDTOService;

@Service
public class DaoDelegateUserDTOService implements UserDTOService {

    private final UserDaoService daoService;

    private final UserDTOMapper dtoMapper;

    public DaoDelegateUserDTOService(UserDaoService daoService, UserDTOMapper dtoMapper) {
        this.daoService = daoService;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public UserDTO create(UserRegistrationData registrationData) {
        User user = daoService.create(registrationData);
        return dtoMapper.convert(user);
    }

    @Override
    public UserDTO update(UserUpdateData updateData) {
        User user = daoService.update(updateData);
        return dtoMapper.convert(user);
    }

}
