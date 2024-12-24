package thinh1.restapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import thinh1.restapi.dto.ProfileDTO;
import thinh1.restapi.entity.ProfileEntity;
import thinh1.restapi.reponsitory.ProfileReponsitory;
import thinh1.restapi.service.ProfileService;

import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileReponsitory profileReponsitory;
    private final ModelMapper modelMapper;

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        ProfileEntity profileEntity =  mapToProfileEntity(profileDTO);
        profileEntity.setProfileId(UUID.randomUUID().toString());
        profileEntity = profileReponsitory.save(profileEntity);
        return mapToProfileDTO(profileEntity);


    }

    private ProfileDTO mapToProfileDTO(ProfileEntity profileEntity) {
        return modelMapper.map(profileEntity, ProfileDTO.class);
    }

    private ProfileEntity mapToProfileEntity(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileEntity.class);

    }
    }

