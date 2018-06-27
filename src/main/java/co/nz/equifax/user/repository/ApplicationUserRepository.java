package co.nz.equifax.user.repository;

import co.nz.equifax.user.entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {

  ApplicationUser findByUsername(String username);

}
