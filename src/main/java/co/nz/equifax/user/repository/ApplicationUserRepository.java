package co.nz.equifax.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.nz.equifax.user.entities.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

	Optional<ApplicationUser> findByUsername(String username);

	List<ApplicationUser> findByIdIn(List<Long> userIds);

	Boolean existsByUsername(String username);

}
