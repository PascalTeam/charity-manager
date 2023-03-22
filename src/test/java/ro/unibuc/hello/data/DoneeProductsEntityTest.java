package ro.unibuc.hello.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoneeProductsEntityTest {
  List<DoneeProductEntity> donneProducts = new ArrayList<>();
  DoneeProductsEntity doneeProductsEntity;

  public DoneeProductsEntityTest() {
    donneProducts.add(new DoneeProductEntity("id1", 2));
    donneProducts.add(new DoneeProductEntity("id2", 5));

    doneeProductsEntity = new DoneeProductsEntity("doneeId", donneProducts);
  }

  @Test
  void test_donee_id() {
    Assertions.assertSame("doneeId", doneeProductsEntity.getDoneeId());
  }

  @Test
  void test_products_ids() {
    var doneeProductsIds = doneeProductsEntity.getProducts().stream().map(p -> p.productId).collect(Collectors.toList());
    var expectedProductIds = new ArrayList<String>();
    expectedProductIds.add("id1");
    expectedProductIds.add("id2");
    
    Assertions.assertEquals(expectedProductIds, doneeProductsIds);
  }

  @Test
  void test_products_quantities() {
    var doneeProductsQuantities = doneeProductsEntity.getProducts().stream().map(p -> p.quantity).collect(Collectors.toList());
    var expectedProductQuantities = new ArrayList<Integer>();
    expectedProductQuantities.add(2);
    expectedProductQuantities.add(5);
    
    Assertions.assertEquals(expectedProductQuantities, doneeProductsQuantities);
  }
}
