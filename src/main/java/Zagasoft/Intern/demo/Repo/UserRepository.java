package Zagasoft.Intern.demo.Repo;

import Zagasoft.Intern.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    public boolean existsByEmail(String email);
}
