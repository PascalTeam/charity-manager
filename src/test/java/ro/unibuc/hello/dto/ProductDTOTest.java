package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductDTOTest {
  ProductDTO productDTO = new ProductDTO("Milk", 4);

  @Test
  public void testProductDTO() {
    Assertions.assertEquals("Milk", productDTO.name);
    Assertions.assertEquals(4, productDTO.quantity);
  }
}
