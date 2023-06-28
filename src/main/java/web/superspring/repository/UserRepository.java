package web.superspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.superspring.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUsersByEmail(String email);
}
