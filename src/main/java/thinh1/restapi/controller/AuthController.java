package thinh1.restapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import thinh1.restapi.dto.ProfileDTO;
import thinh1.restapi.io.ProfileRequest;
import thinh1.restapi.io.ProfileResponse;
import thinh1.restapi.service.ProfileService;


@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final ModelMapper modelMapper;
    private final ProfileService profileService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ProfileResponse createProfile(  @Valid @RequestBody ProfileRequest profileRequest) {
        log.info("Create profile {}", profileRequest);
        ProfileDTO profileDTO =  maptoProfileDTO(profileRequest);
        profileDTO =  profileService.createProfile(profileDTO);
        return mapToProfileResponse(profileDTO);
    }

    private ProfileDTO maptoProfileDTO(@Valid ProfileRequest profileRequest) {
        return modelMapper.map(profileRequest, ProfileDTO.class);
    }

    private ProfileResponse mapToProfileResponse(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileResponse.class);
    }
}
