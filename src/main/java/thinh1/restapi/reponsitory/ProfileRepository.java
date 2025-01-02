package thinh1.restapi.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import thinh1.restapi.entity.ProfileEntity;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    Optional<ProfileEntity> findByEmail(String email);
    Boolean existsByEmail(String email);


}
