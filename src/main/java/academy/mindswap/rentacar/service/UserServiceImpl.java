package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.converter.UserConverter;
import academy.mindswap.rentacar.dto.UserCreateDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.model.Token;
import academy.mindswap.rentacar.model.User;
import academy.mindswap.rentacar.model.UserRole;
import academy.mindswap.rentacar.repository.TokenRepository;
import academy.mindswap.rentacar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserConverter userConverter;
    private TokenRepository tokenRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, TokenRepository tokenRepository){
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        if (!userCreateDto.getPassword().equals(userCreateDto.getRetypedPassword())){
            throw new IllegalArgumentException("Passwords do not match");
        }
        User user = userConverter.fromUserCreateDtoToEntity(userCreateDto);
        user = userRepository.save(user);
        return userConverter.fromUserEntityToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.getReferenceById(userId);
        return userConverter.fromUserEntityToUserDto(user);
    }
    @Override
    public UserDto getUserByFirstName(String firstName) {
      User user = userRepository.getUserByFirstName(firstName);
        return userConverter.fromUserEntityToUserDto(user);
    }
    @Secured("ADMIN")
    @Override
    public UserDto updateUserRoleToAdmin(Long userId) {
        User user = userRepository.getReferenceById(userId);
        if(user == null){
            throw new IllegalArgumentException("User not found.");
        }
        user.setRole(UserRole.ADMIN);
        User updatedUser = userRepository.save(user);
        return userConverter.fromUserEntityToUserDto(updatedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.parallelStream()
                .map(userConverter::fromUserEntityToUserDto)
                .toList();
        return userDtos;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        User existingUser = userRepository.getReferenceById(id);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found.");
        }
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return userConverter.fromUserEntityToUserDto(updatedUser);
    }
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.getReferenceById(userId);
        if(user == null){
            throw new IllegalArgumentException("User not found.");
        }
        List<Token> tokens = tokenRepository.findAllValidTokenByUser(userId);

        tokenRepository.deleteAll(tokens);
        userRepository.delete(user);

    }



}
