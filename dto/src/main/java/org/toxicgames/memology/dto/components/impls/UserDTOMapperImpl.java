package org.toxicgames.memology.dto.components.impls;

import org.springframework.stereotype.Component;
import org.toxicgames.memology.dao.entities.User;
import org.toxicgames.memology.dto.objects.UserDTO;
import org.toxicgames.memology.dto.components.UserDTOMapper;

@Component
public class UserDTOMapperImpl implements UserDTOMapper {

    @Override
    public UserDTO convert(User model) {
        return new UserDTO(model.getId(), model.getTelegramData().getId(), model.getName());
    }

}
