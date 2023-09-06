package com.betrybe.agrix.services;

import com.betrybe.agrix.dtos.CropResponseDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * Class responsible for the application's service layer.
 */
@Service
public class CropService {
  private CropRepository cropRepository;

  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  public Crop createCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  /**
   * Service layer method responsible for bringing all crops.
   */
  public List<CropResponseDto> getAllCrops() {
    List<Crop> crops = cropRepository.findAll();

    return crops.stream()
        .map(crop -> new CropResponseDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId()))
        .collect(Collectors.toList());
  }

  /**
   * Service layer method responsible for bringing one crop.
   */
  public Optional<Crop> getCropById(Integer id) {
    return cropRepository.findById(id);
  }

  public List<Crop> getCropsByFarmId(Integer farmId) {
    return cropRepository.findByFarmId(farmId);
  }

  /**
   * Service layer method responsible for bringing a list of crops within a given
   * period.
   */
  public List<CropResponseDto> getCropByDate(
      LocalDate initialHarvestDate, LocalDate finalHarvestDate) {
    List<Crop> allCrops = cropRepository.findAll();

    return allCrops.stream()
        .filter(crop -> crop.getHarvestDate().isAfter(initialHarvestDate)
            && crop.getHarvestDate().isBefore(finalHarvestDate))
        .map(crop -> new CropResponseDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId()))
        .collect(Collectors.toList());
  }
}