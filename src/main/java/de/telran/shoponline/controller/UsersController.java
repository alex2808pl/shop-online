package de.telran.shoponline.controller;

import de.telran.shoponline.dto.UsersDto;
import de.telran.shoponline.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {
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
}
