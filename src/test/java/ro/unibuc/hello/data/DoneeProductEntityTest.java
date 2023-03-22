package ro.unibuc.hello.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoneeProductEntityTest {
  DoneeProductEntity doneeProduct = new DoneeProductEntity("id", 10);
  
  @Test
  void test_product_id() {
    Assertions.assertSame("id", doneeProduct.getProductId());
  }

  @Test
  void test_quantity() {
    Assertions.assertSame(10, doneeProduct.getQuantity());
  }
}
