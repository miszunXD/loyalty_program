package com.miszunXD.loyalty_program.service;

import com.miszunXD.loyalty_program.dto.UserCommand;
import com.miszunXD.loyalty_program.dto.UserDto;
import com.miszunXD.loyalty_program.exception.EmailAlreadyTakenException;
import com.miszunXD.loyalty_program.exception.UserNotFoundException;
import com.miszunXD.loyalty_program.mapper.UserMapper;
import com.miszunXD.loyalty_program.model.User;
import com.miszunXD.loyalty_program.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserDto create(UserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new EmailAlreadyTakenException(command.email());
        }
        User user = userMapper.toEntity(command);
        user.setRegisteredAt(LocalDateTime.now());
        return userMapper.toDto(userRepository.save(user));
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto findById(Long id) {
        return userMapper.toDto(getByIdOrThrow(id));
    }

    public UserDto update(Long id, UserCommand command) {
        User existing = getByIdOrThrow(id);
        if (userRepository.existsByEmailAndIdNot(command.email(), id)) {
            throw new EmailAlreadyTakenException(command.email());
        }
        userMapper.updateEntity(command, existing);
        return userMapper.toDto(userRepository.save(existing));
    }

    public void delete(Long id) {
        User user = getByIdOrThrow(id);
        userRepository.delete(user);
    }

    private User getByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
