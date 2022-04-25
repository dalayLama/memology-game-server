package org.toxicgames.memology.dto.components;

public interface DTOMapper<M, DTO> {

    DTO convert(M model);

}
