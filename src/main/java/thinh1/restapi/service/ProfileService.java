package thinh1.restapi.service;

import org.springframework.context.annotation.Profile;
import thinh1.restapi.dto.ProfileDTO;

public interface ProfileService {
    ProfileDTO createProfile(ProfileDTO profileDTO);



}
