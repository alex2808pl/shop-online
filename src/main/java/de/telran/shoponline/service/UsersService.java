package de.telran.shoponline.service;

import de.telran.shoponline.config.MapperUtil;
import de.telran.shoponline.dto.UsersDto;
import de.telran.shoponline.entity.Users;
import de.telran.shoponline.mapper.Mappers;
import de.telran.shoponline.repositiry.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final  Mappers mappers;
    private final  ModelMapper modelMapper;

//    public UsersService(UsersRepository usersRepository, Mappers mappers, ModelMapper modelMapper) {
//        this.usersRepository = usersRepository;
//        this.mappers = mappers;
//        this.modelMapper = modelMapper;
//    }

    public List<UsersDto> getUsers() {
        List<Users> users = new ArrayList<>();
        usersRepository.findAll().forEach(users::add);
        List<UsersDto> usersList = MapperUtil.convertList(users, mappers::convertToUsersDto);
        return usersList;
    }

    public UsersDto getUserById(Long id) {
        Users user = usersRepository.findById(id).orElse(null);
        UsersDto userDto = mappers.convertToUsersDto(user);
        return userDto;
    }
}
