package ro.unibuc.hello.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddProductsDTO {
  public List<ProductDTO> products;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public AddProductsDTO(@JsonProperty("products") List<ProductDTO> products) {
    this.products = products;
  }

  @Override
  public String toString() {
    return products.toString();
  }
}
