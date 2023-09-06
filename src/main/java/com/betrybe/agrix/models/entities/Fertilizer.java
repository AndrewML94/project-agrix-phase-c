package com.betrybe.agrix.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Class responsible for parameterized attributes of the Fertilizer table.
 */
@Entity
@Table(name = "fertilizer")
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String brand;

  private String composition;

  @ManyToMany(mappedBy = "fertilizer")
  @JsonIgnore
  private List<Crop> crop;

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
   * Method responsible for capturing the brand.
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Method responsible for inserting the brand.
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Method responsible for capturing the composition.
   */
  public String getComposition() {
    return composition;
  }

  /**
   * Method responsible for inserting the composition.
   */
  public void setComposition(String composition) {
    this.composition = composition;
  }

  /**
   * Method responsible for capturing the crop list.
   */
  public List<Crop> getCrop() {
    return crop;
  }

  /**
   * Method responsible for inserting the crop list.
   */
  public void setCrop(List<Crop> crop) {
    this.crop = crop;
  }

}
