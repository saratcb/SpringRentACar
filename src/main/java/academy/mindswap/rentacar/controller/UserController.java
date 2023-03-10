package academy.mindswap.rentacar.controller;

import academy.mindswap.rentacar.dto.UserCreateDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.model.User;
import academy.mindswap.rentacar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    //@Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

  @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userService.getAllUsers();
    return new ResponseEntity<>(userDtos, HttpStatus.OK);
}
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreateDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors){
                System.out.println(error.getObjectName() + "-" + error.getDefaultMessage());
            }
          //  bindingResult.getAllErrors().forEach(System.out::println);
           // throw  new IllegalArgumentException("Invalid user");
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id){
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
