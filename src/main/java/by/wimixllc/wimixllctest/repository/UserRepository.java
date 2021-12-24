package by.wimixllc.wimixllctest.repository;

import by.wimixllc.wimixllctest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByPhoneNumber(String phone);
    boolean existsByEmail(String email);
}
