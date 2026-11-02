package com.miszunXD.loyalty_program.mapper;

import com.miszunXD.loyalty_program.dto.UserCommand;
import com.miszunXD.loyalty_program.dto.UserDto;
import com.miszunXD.loyalty_program.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCommand command) {
        User user = new User();
        updateEntity(command, user);
        return user;
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRegisteredAt()
        );
    }

    public void updateEntity(UserCommand command, User user) {
        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
        user.setEmail(command.email());
    }
}
