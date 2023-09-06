package com.betrybe.agrix.controllers;

import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FertilizerController {
  @Autowired
  private FertilizerService fertilizerService;

  /**
   * Application post method for creating a new fertilizer.
   */
  @PostMapping("/fertilizers")
  public ResponseEntity<Fertilizer> createFertilizer(
      @RequestBody Fertilizer fertilizer) {
    Fertilizer newFertilizer = fertilizerService.createFertilizers(fertilizer);

    return ResponseEntity.status(HttpStatus.CREATED).body(newFertilizer);
  }

  /**
  * Application get method to get all fertilizers.
  */
  @GetMapping("/fertilizers")
  @Secured("ADMIN")
  public ResponseEntity<List<Fertilizer>> getAllFertilizer() {
    List<Fertilizer> allFertilizer = fertilizerService.getAllFertilizer();

    return ResponseEntity.ok().body(allFertilizer);
  }

  /**
  * Get method of the application to get a fertilizer by id.
  */
  @GetMapping("/fertilizers/{fertilizerId}")
  public ResponseEntity<Object> getFarmById(@PathVariable(value = "fertilizerId") Integer id) {
    Optional<Fertilizer> fertilizer = fertilizerService.getFertilizerById(id);

    if (fertilizer.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
    }

    return ResponseEntity.status(HttpStatus.OK).body(fertilizer);
  }
}
