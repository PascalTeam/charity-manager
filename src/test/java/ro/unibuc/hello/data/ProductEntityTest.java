package ro.unibuc.hello.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductEntityTest {
  ProductEntity product = new ProductEntity("name", 9);

  @Test
  void testName() {
    Assertions.assertSame("name", product.name);
  }

  @Test
  void testQuantity() {
    Assertions.assertSame(9, product.quantity);
  }
}
