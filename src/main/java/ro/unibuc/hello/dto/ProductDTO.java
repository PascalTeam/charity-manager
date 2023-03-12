package ro.unibuc.hello.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {
  public String name;
  public int quantity;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public ProductDTO(@JsonProperty("name") String name, @JsonProperty("quantity") int quantity) {
    this.name = name;
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "ProductDTO[" +
        "name='" + name + '\'' +
        ", quantity=" + quantity +
        ']';
  }
}
