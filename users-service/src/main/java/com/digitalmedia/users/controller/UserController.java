package com.digitalmedia.users.controller;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UserRequest;
import com.digitalmedia.users.repository.KeycloakRepository;
import com.digitalmedia.users.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final IUserService userService;
  private final KeycloakRepository keycloakRepository;


  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('GROUP_admin')")
  public List<UserRepresentation> getAllUsers(){
    return this.keycloakRepository.getAllUsers();
  }

  @GetMapping("/me")
  public User getUserExtra(Principal principal) {
    return userService.validateAndGetUserExtra(principal.getName());
  }

  @PostMapping("/me")
  public User saveUserExtra(@Valid @RequestBody UserRequest updateUserRequest, Principal principal) {
    Optional<User> userOptional = userService.getUserExtra(principal.getName());
    User userExtra = userOptional.orElseGet(() -> new User(principal.getName()));
    userExtra.setAvatar(updateUserRequest.getAvatar());
    return userService.saveUserExtra(userExtra);
  }

}
