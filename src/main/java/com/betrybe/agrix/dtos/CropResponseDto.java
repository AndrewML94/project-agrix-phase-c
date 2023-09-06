package com.betrybe.agrix.dtos;

import java.time.LocalDate;

/**
 * Java Record.
 */
public record CropResponseDto(
    Integer id, String name, Double plantedArea, LocalDate plantedDate, LocalDate harvestDate,
    Integer farmId) {
}
