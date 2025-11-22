package com.login.user.domain.mapper;

import com.login.user.domain.dto.response.AddressResponseDTO;
import com.login.user.domain.dto.response.UserResponseDTO;
import com.login.user.domain.model.Address;
import com.login.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "address", source = "address")
    UserResponseDTO toDto(User user);

    AddressResponseDTO toAddressDto(Address address);
}
