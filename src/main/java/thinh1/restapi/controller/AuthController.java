package thinh1.restapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import thinh1.restapi.dto.ProfileDTO;
import thinh1.restapi.io.AuthRequest;
import thinh1.restapi.io.AuthResponse;
import thinh1.restapi.io.ProfileRequest;
import thinh1.restapi.io.ProfileResponse;
import thinh1.restapi.service.CustomUserDetailsService;
import thinh1.restapi.service.ProfileService;
import thinh1.restapi.util.JwtTokenUtil;

import java.util.UUID;


@RestController
@Slf4j
@RequiredArgsConstructor

public class AuthController {

    private final ModelMapper modelMapper;
    private final ProfileService profileService;
    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final CustomUserDetailsService userDetailsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ProfileResponse createProfile(  @Valid @RequestBody ProfileRequest profileRequest) {
        log.info("Create profile {}", profileRequest);
        ProfileDTO profileDTO =  maptoProfileDTO(profileRequest);
        profileDTO =  profileService.createProfile(profileDTO);
        return mapToProfileResponse(profileDTO);
    }
    @PostMapping("/login")
    public AuthResponse authenticateProfile(@RequestBody AuthRequest authRequest) throws Exception {
        log.info("Authenticate profile {}", authRequest);
        authenticate(authRequest);
        final UserDetails userDetails =  userDetailsService.loadUserByUsername(authRequest.getEmail());
        final String token =  jwtTokenUtil.generateToken(userDetails);
        return new AuthResponse( token, authRequest.getEmail());
    }

    private void authenticate(AuthRequest authRequest) throws Exception{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        }catch(DisabledException ex){
            throw new Exception("Profile disable");
        }catch(BadCredentialsException ex){
            throw new Exception("Bad Credentials");
        }
    }

    private ProfileDTO maptoProfileDTO(@Valid ProfileRequest profileRequest) {
        return modelMapper.map(profileRequest, ProfileDTO.class);
    }

    private ProfileResponse mapToProfileResponse(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileResponse.class);
    }
}
