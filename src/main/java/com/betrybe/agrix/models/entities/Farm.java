package com.betrybe.agrix.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
* Class responsible for parameterized attributes of the Farm table. 
*/
@Entity
@Table(name = "farm")
public class Farm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private Double size;

  /**
  * One-to-many relationship with the Crop table.
  */
  @OneToMany(mappedBy = "farm")
  private List<Crop> crops;
  
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
  * Method responsible for capturing the size.
  */
  public Double getSize() {
    return size;
  }

  /**
  * Method responsible for inserting the size.
  */
  public void setSize(Double size) {
    this.size = size;
  }
}
