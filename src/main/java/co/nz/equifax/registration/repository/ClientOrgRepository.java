package co.nz.equifax.registration.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.nz.equifax.registration.entity.ClientOrg;

@Repository
public interface ClientOrgRepository extends CrudRepository<ClientOrg, Long> {
  Optional<ClientOrg> findByUuid(String uuid);
}
