package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dtos.AuthenticationDto;
import com.betrybe.agrix.dtos.PersonDto;
import com.betrybe.agrix.dtos.TokenDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import com.betrybe.agrix.services.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class responsible for the application's controller layer.
 */
@RestController
public class PersonController {
  private PersonService personService;
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  /**
   * Constructor.
   */
  public PersonController(
      PersonService personService,
      AuthenticationManager authenticationManager,
      TokenService tokenService
  ) {
    this.personService = personService;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Application post method for creating a new user.
   */
  @PostMapping("/persons")
  public ResponseEntity<PersonDto> createUser(@RequestBody Person person) {
    Person newPerson = personService.create(person);
    PersonDto returnPersonDto = new PersonDto(
        newPerson.getId(), newPerson.getUsername(), newPerson.getRole()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(returnPersonDto);
  }

  /**
   * Application post method for login.
   */
  @PostMapping("/auth/login")
  public ResponseEntity<TokenDto> login(@RequestBody AuthenticationDto authenticationDto) {

    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(
        authenticationDto.username(), authenticationDto.password()
    );
    Authentication auth = authenticationManager.authenticate(usernamePassword);
    Person person = (Person) auth.getPrincipal();

    String token = tokenService.generateToken(person);

    TokenDto response = new TokenDto(token);

    System.out.println(response);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
