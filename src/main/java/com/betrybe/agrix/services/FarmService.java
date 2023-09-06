package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
* Class responsible for the application's service layer.
*/
@Service
public class FarmService {
  private FarmRepository farmRepository;

  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  public Optional<Farm> getFarmById(Integer id) {
    return farmRepository.findById(id);
  }
}
