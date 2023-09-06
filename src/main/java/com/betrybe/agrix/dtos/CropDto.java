package com.betrybe.agrix.dtos;

import com.betrybe.agrix.models.entities.Farm;
import java.time.LocalDate;

/**
 * Java Record.
 */
public record CropDto(
    String name, Double plantedArea, LocalDate plantedDate, LocalDate harvestDate, Farm farm) {
}
