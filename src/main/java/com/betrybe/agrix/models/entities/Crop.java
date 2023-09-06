package com.betrybe.agrix.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * Class responsible for parameterized attributes of the Crop table.
 */
@Entity
@Table(name = "crop")
public class Crop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private Double plantedArea;

  @Column(name = "planted_date")
  private LocalDate plantedDate;

  @Column(name = "harvest_date")
  private LocalDate harvestDate;

  /**
   * Many-to-one relationship with the Farm table.
   */
  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;

  @ManyToMany
  @JoinTable(
      name = "crop_fertilizer",
      joinColumns = @JoinColumn(name = "fertilizer_id"),
      inverseJoinColumns = @JoinColumn(name = "crop_id")
  )
  private List<Fertilizer> fertilizer;

  /**
   * Method responsible for capturing the id.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Method responsible for inserting the id.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Method responsible for capturing the farmId.
   */
  public Farm getFarm() {
    return farm;
  }

  /**
   * Method responsible for inserting the farmId.
   */
  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  /**
   * Method responsible for capturing the name.
   */
  public String getName() {
    return name;
  }

  /**
   * Method responsible for inserting the name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Method responsible for capturing the plantedArea.
   */
  public Double getPlantedArea() {
    return plantedArea;
  }

  /**
   * Method responsible for inserting the plantedArea.
   */
  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  /**
   * Method responsible for capturing the plantedDate.
   */
  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  /**
   * Method responsible for inserting the plantedDate.
   */
  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  /**
   * Method responsible for capturing the harvestDate.
   */
  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  /**
   * Method responsible for inserting the harvestDate.
   */
  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  /**
   * Method responsible for capturing the fertilizer list.
   */
  public List<Fertilizer> getFertilizer() {
    return fertilizer;
  }

  /**
   * Method responsible for inserting the fertilizer list.
   */
  public void setFertilizer(List<Fertilizer> fertilizer) {
    this.fertilizer = fertilizer;
  }

}
