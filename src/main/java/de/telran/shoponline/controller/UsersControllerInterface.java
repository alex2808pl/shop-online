package de.telran.shoponline.controller;


import de.telran.shoponline.dto.UsersDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name="Контроллер пользователей", description="Контроллер для работы с пользователями")
public interface UsersControllerInterface {

    @Operation(
            summary = "Получить информацию о клиенте",
            description = "Позволяет информация о клиенте по его Id"
    )
    public ResponseEntity<UsersDto> getUserById(@PathVariable Long id);
}
