package de.telran.shoponline.repository;

import de.telran.shoponline.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<Users,Long> {
    @Query("SELECT u FROM Users u WHERE u.email=?1")
    List<Users> getByEmail(String email);
}
