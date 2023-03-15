package ro.unibuc.hello.dto;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignProductsDoneeDTO {
  public String doneeId;
  public List<DoneeProductDTO> products;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public AssignProductsDoneeDTO(
      @JsonProperty("doneeId") String doneeId,
      @JsonProperty("productsIds") List<DoneeProductDTO> products
  ) {
    this.doneeId = doneeId;
    this.products = products;
  }
}
