package co.nz.equifax.registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.nz.equifax.registration.entity.ClientOrg;
import co.nz.equifax.registration.entity.UuidCode;

@Repository
public interface UuidCodeRepository extends JpaRepository<UuidCode, Long> {
	
	Optional<UuidCode> findUuidCodeByUuidAndCode(String uuid,long code);
	  

}
