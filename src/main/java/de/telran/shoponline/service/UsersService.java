package de.telran.shoponline.service;

import de.telran.shoponline.config.MapperUtil;
import de.telran.shoponline.dto.CartDto;
import de.telran.shoponline.dto.UsersDto;
import de.telran.shoponline.entity.Cart;
import de.telran.shoponline.entity.Users;
import de.telran.shoponline.exceptions.ErrorParamException;
import de.telran.shoponline.exceptions.NotFoundInDbException;
import de.telran.shoponline.mapper.Mappers;
import de.telran.shoponline.repository.CartRepository;
import de.telran.shoponline.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.actuate.endpoint.invoke.ParameterMappingException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j //для включения логгирования
@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final CartRepository cartRepository;
    private final  Mappers mappers;
    private final  ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

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
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new NotFoundInDbException("Не найден пользователь с таким Id"));
        UsersDto userDto = mappers.convertToUsersDto(user);
        return userDto;
    }

    public UsersDto updateUser(UsersDto usersDto) throws FileNotFoundException {
        if(usersDto.getUserID()!=null) {
            Optional<Users> usersOptional = usersRepository.findById(usersDto.getUserID());
            if(usersOptional.isPresent()) {
                Users users = usersOptional.get();
                users.setUserID((usersDto.getUserID()!=null) ? usersDto.getUserID() : users.getUserID());
                users.setName((usersDto.getName()!=null) ? usersDto.getName() : users.getName());
                users.setEmail((usersDto.getEmail()!=null) ? usersDto.getEmail() : users.getEmail());
                users.setPhoneNumber((usersDto.getPhoneNumber()!=null) ? usersDto.getPhoneNumber() : users.getPhoneNumber());
                users.setRole((usersDto.getRole()!=null) ? usersDto.getRole() : users.getRole());
                // пароль пока не пробуем
                // Связанный объект нужно найти в репозитории по коду и тоже передать его в переменную?
                Users userUpdated = usersRepository.save(users);
                return mappers.convertToUsersDto(userUpdated);
            } else {
                log.error("---- Не найден пользователь с Id="+usersDto.getUserID());
                throw new NotFoundInDbException("Не найден пользователь с Id="+usersDto.getUserID());
            }
        } else {
            throw new FileNotFoundException("Не корректный параметр usersDto");
        }
    }

    // Security
    public UsersDto getByEmail(String email) {
        List<Users> users = usersRepository.getByEmail(email);
        UsersDto usersDto = null;
        if(users!=null && !users.isEmpty())
            usersDto = mappers.convertToUsersDto(users.getFirst());
        else
            new NotFoundInDbException("Не найден в БД пользователь с e-mail = "+email);

        return usersDto;
    }

    public UsersDto createUser(UsersDto userCredentialsDto) {
        // проверить в БД существование такого же e-mail у другого пользователя
        userCredentialsDto.setUserID(null); //добавление нового пользователя
        Users users = mappers.convertToUsers(userCredentialsDto);
        //хэшируем пароль
        users.setPasswordHash(passwordEncoder.encode(userCredentialsDto.getPasswordHash()));

        CartDto cartDto = userCredentialsDto.getCartDto();
        Cart cart = null;
        if(cartDto!=null) {
            cart = cartRepository.findById(cartDto.getCartID())
                    .orElseThrow(()->new NotFoundInDbException("Не найдена в БД корзина с Id="+cartDto.getCartID()));
        }
        Users userEntityResponse = usersRepository.save(users);

        return mappers.convertToUsersDto(userEntityResponse);
    }
}
