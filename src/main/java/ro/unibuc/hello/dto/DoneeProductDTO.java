package ro.unibuc.hello.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DoneeProductDTO {
  public String productId;
  public int quantity;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public DoneeProductDTO(
      @JsonProperty("productId") String productId,
      @JsonProperty("quantity") int quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

}
