package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dtos.CropDto;
import com.betrybe.agrix.dtos.CropResponseDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class responsible for the application's controller layer.
 */
@RestController
public class CropController {
  private CropService cropService;
  private FarmService farmService;

  public CropController(CropService cropService, FarmService farmService) {
    this.cropService = cropService;
    this.farmService = farmService;
  }

  /**
   * Application post method for creating a new crop.
   */
  @PostMapping("/farms/{farmId}/crops")
  public ResponseEntity<Object> createCrop(
      @PathVariable(value = "farmId") Integer farmId,
      @RequestBody CropDto cropDto) {
    Optional<Farm> farm = farmService.getFarmById(farmId);
    var crop = new Crop();

    if (farm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    BeanUtils.copyProperties(cropDto, crop);
    Farm existingFarm = farm.get();
    crop.setFarm(existingFarm);

    Crop createdCrop = cropService.createCrop(crop);

    Map<String, Object> response = new HashMap<>();
    response.put("id", createdCrop.getId());
    response.put("name", createdCrop.getName());
    response.put("plantedArea", createdCrop.getPlantedArea());
    response.put("plantedDate", createdCrop.getPlantedDate());
    response.put("harvestDate", createdCrop.getHarvestDate());
    response.put("farmId", existingFarm.getId());

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  /**
   * Get method of the application responsible for capturing all crops.
   */
  @GetMapping("/farms/{farmId}/crops")
  public ResponseEntity<?> getAllCropsFromTheFarm(@PathVariable(value = "farmId") Integer farmId) {
    Optional<Farm> farm = farmService.getFarmById(farmId);

    if (farm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    List<Crop> crops = cropService.getCropsByFarmId(farmId);

    List<CropResponseDto> cropResponseDtos = crops.stream()
        .map(crop -> new CropResponseDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId()))
        .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(cropResponseDtos);
  }

  /**
   * Application get method to get all crops.
   */
  @GetMapping("/crops")
  @Secured({"MANAGER", "ADMIN"})
  public ResponseEntity<List<CropResponseDto>> getAllCrops() {
    return ResponseEntity.ok().body(cropService.getAllCrops());
  }

  /**
   * Application get method to get one crop.
   */
  @GetMapping("/crops/{id}")
  public ResponseEntity<?> getCropById(@PathVariable(value = "id") Integer id) {
    Optional<Crop> crop = cropService.getCropById(id);

    if (crop.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
    }

    CropResponseDto cropResponseDto = new CropResponseDto(
        crop.get().getId(),
        crop.get().getName(),
        crop.get().getPlantedArea(),
        crop.get().getPlantedDate(),
        crop.get().getHarvestDate(),
        crop.get().getFarm().getId());

    return ResponseEntity.ok().body(cropResponseDto);
  }

  /**
   * Get method of the application that returns a list of crops within a period of
   * time.
   */
  @GetMapping("/crops/search")
  public ResponseEntity<List<CropResponseDto>> getCropByDate(
      @RequestParam(name = "start") LocalDate initialHarvestDate,
      @RequestParam(name = "end") LocalDate finalHarvestDate) {

    List<CropResponseDto> allCrops = cropService
        .getCropByDate(initialHarvestDate, finalHarvestDate);

    return ResponseEntity.ok().body(allCrops);
  }
}
