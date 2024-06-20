package de.telran.shoponline.controller;

import de.telran.shoponline.dto.UsersDto;
import de.telran.shoponline.exceptions.ErrorParamException;
import de.telran.shoponline.exceptions.NotFoundInDbException;
import de.telran.shoponline.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController implements UsersControllerInterface {
    @Autowired
    UsersService usersService;

    @GetMapping
    public ResponseEntity<List<UsersDto>> getUsers() {
        List<UsersDto> users = usersService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsersDto> getUserById(@PathVariable Long id) {
        UsersDto user = usersService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UsersDto> updateClient(@RequestBody @Valid UsersDto usersDto) throws FileNotFoundException {
        UsersDto client = usersService.updateUser(usersDto);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @ExceptionHandler(ErrorParamException.class)
    public ResponseEntity<ErrorMessage> handleException(ErrorParamException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(NotFoundInDbException.class)
    public ResponseEntity<ErrorMessage> handleException(NotFoundInDbException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()+"!!!!!!!!!!"));
    }
}
