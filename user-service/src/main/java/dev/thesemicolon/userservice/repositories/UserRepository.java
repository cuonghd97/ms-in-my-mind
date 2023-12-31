package dev.thesemicolon.userservice.repositories;

import dev.thesemicolon.userservice.daos.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);
}
