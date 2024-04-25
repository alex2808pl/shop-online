package de.telran.shoponline.service;

import de.telran.shoponline.dto.UsersDto;
import de.telran.shoponline.entity.Users;
import de.telran.shoponline.entity.enums.Role;
import de.telran.shoponline.mapper.Mappers;
import de.telran.shoponline.repository.CartRepository;
import de.telran.shoponline.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
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

    private UsersDto expectedUserDto;
    private Users expectedUser;

    @BeforeEach
    void setUp() {
        expectedUserDto = UsersDto.builder()
                .userID(1L)
                .email("test@test.com")
                .role(Role.CLIENT)
                .name("Test")
                .phoneNumber("123456789")
                .passwordHash("******")
                .build();
        expectedUser = Users.builder()
                .userID(1L)
                .email("test@test.com")
                .role(Role.CLIENT)
                .name("Test")
                .phoneNumber("123456789")
                .passwordHash("******")
                .build();
    }

    @Test
    void getUsersTest() {
        when(usersRepositoryMock.findAll()).thenReturn(Arrays.asList(expectedUser));
        when(mappersMock.convertToUsersDto(any(Users.class))).thenReturn(expectedUserDto);
        List<UsersDto> actualUserDtoList = usersServiceTest.getUsers();
        assertEquals(Arrays.asList(expectedUserDto), actualUserDtoList);
    }

    @Test
    void getUserByIdTest() {

        when(usersRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedUser));
        when(mappersMock.convertToUsersDto(any(Users.class))).thenReturn(expectedUserDto);

        UsersDto actualUserDto = usersServiceTest.getUserById(1L);
        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    void updateUserTest() {

    }
}