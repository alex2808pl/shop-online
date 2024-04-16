package de.telran.shoponline.service;

import de.telran.shoponline.dto.UsersDto;
import de.telran.shoponline.entity.Users;
import de.telran.shoponline.entity.enums.Role;
import de.telran.shoponline.mapper.Mappers;
import de.telran.shoponline.repository.CartRepository;
import de.telran.shoponline.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {
    @Mock
    private UsersRepository usersRepositoryMock;

    @Mock
    private Mappers mappersMock;

    @Mock
    private ModelMapper modelMapperMock;

    @InjectMocks
    private UsersService usersServiceTest;

    @Test
    void getUsersTest() {
        UsersDto expectedUser = UsersDto.builder()
                .userID(1L)
                .email("test@test.com")
                .role(Role.CLIENT)
                .name("Test")
                .phoneNumber("123456789")
                .passwordHash("******")
                .build();

//        when(usersRepositoryMock.findAll()).thenReturn((Iterable<Users>) List.of(expectedUser));

    }

    @Test
    void getUserByIdTest() {
        UsersDto expectedUserDto = UsersDto.builder()
                .userID(1L)
                .email("test@test.com")
                .role(Role.CLIENT)
                .name("Test")
                .phoneNumber("123456789")
                .passwordHash("******")
                .build();

        Users expectedUser = Users.builder()
                .userID(1L)
                .email("test@test.com")
                .role(Role.CLIENT)
                .name("Test")
                .phoneNumber("123456789")
                .passwordHash("******")
                .build();

        when(usersRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedUser));
        when(mappersMock.convertToUsersDto(any(Users.class))).thenReturn(expectedUserDto);

        UsersDto actualUserDto = usersServiceTest.getUserById(1L);
        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    void updateUserTest() {

    }
}