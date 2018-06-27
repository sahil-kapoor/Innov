package co.nz.equifax.registration.repository;

import co.nz.equifax.registration.entity.ClientOrg;
import co.nz.equifax.task.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientOrgRepository extends CrudRepository<ClientOrg, Long> {
  Optional<ClientOrg> findByUuid(String uuid);
}
