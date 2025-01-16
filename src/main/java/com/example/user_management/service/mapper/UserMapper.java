package com.example.user_management.service.mapper;

import com.example.user_management.model.User;
import com.example.user_management.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * The interface User mapper.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    /**
     * To user dto user dto.
     *
     * @param user the user
     * @return the user dto
     */
    UserDTO toUserDTO(User user);

    /**
     * To user user.
     *
     * @param userDTO the user dto
     * @return the user
     */
    User toUser(UserDTO userDTO);

}
