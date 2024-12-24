package thinh1.restapi.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import thinh1.restapi.entity.ProfileEntity;

public interface ProfileReponsitory extends JpaRepository<ProfileEntity, Long> {
}
