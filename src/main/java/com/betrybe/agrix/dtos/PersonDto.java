package com.betrybe.agrix.dtos;

import com.betrybe.agrix.security.Role;

/**
* Java Record.
*/
public record PersonDto(Long id, String username, Role role) {
}
