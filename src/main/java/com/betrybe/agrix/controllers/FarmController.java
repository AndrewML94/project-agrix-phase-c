package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dtos.FarmDto;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
* Class responsible for the application's controller layer.
*/
@RestController
public class FarmController {
  private FarmService farmService;

  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
  * Application post method for creating a new farm.
  */
  @PostMapping("/farms")
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    var farm = new Farm();
    BeanUtils.copyProperties(farmDto, farm);
    return ResponseEntity.status(HttpStatus.CREATED).body(farmService.createFarm(farm));
  }

  /**
  * Application get method to get all farms.
  */
  @GetMapping("/farms")
  @Secured({"USER", "MANAGER", "ADMIN"})
  public ResponseEntity<List<Farm>> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();
    return ResponseEntity.status(HttpStatus.OK).body(allFarms); 
  }

  /**
  * Get method of the application to get a farm by id.
  */
  @GetMapping("/farms/{id}")
  public ResponseEntity<Object> getFarmById(@PathVariable(value = "id") Integer id) {
    Optional<Farm> farm = farmService.getFarmById(id);

    if (farm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    return ResponseEntity.status(HttpStatus.OK).body(farm);
  }
}
