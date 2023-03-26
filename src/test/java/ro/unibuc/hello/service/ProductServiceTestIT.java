package ro.unibuc.hello.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ro.unibuc.hello.data.CharityEventEntity;
import ro.unibuc.hello.data.CharityEventRepository;
import ro.unibuc.hello.data.ProductEntity;

@SpringBootTest
@Tag("IT")
public class ProductServiceTestIT {
  @Autowired
  CharityEventRepository charityEventRepository;

  @Autowired
  ProductService productService;

  CharityEventEntity charity;
  List<ProductEntity> productEntities = new ArrayList<>();

  @BeforeEach
  public void init() {
    // Creating a new charity event.
    var newCharity = new CharityEventEntity(
        "charity#1",
        "location#1",
        "19-04-2023");
    newCharity.setProducts(new ArrayList<>());

    charity = charityEventRepository.insert(newCharity);

    // Adding products to the charity event.
    IntStream.range(1, 5)
        .forEach(i -> {
          var productEntity = new ProductEntity(
              String.format("product #%s", i),
              i + 5);

          var id = new ObjectId();
          productEntity.setId(id.toString());

          productEntities.add(productEntity);
        });
    charity.products.addAll(productEntities);

    charityEventRepository.save(charity);
  }

  @AfterEach
  public void teardown() {
    charityEventRepository.deleteAll();
  }

  @Test
  void test_delete_product() {
    String charityId = charity.getId();
    String productId = productEntities.get(0).id;

    var productsCount = productService.getProductsForCharity(charityId).size();
    Assertions.assertSame(4, productsCount);

    productService.deleteProductFromCharity(charityId, productId);
    productsCount = productService.getProductsForCharity(charityId).size();
    Assertions.assertSame(3, productsCount);

    String randomProductId = "randomProductId";
    productService.deleteProductFromCharity(charityId, randomProductId);
    productsCount = productService.getProductsForCharity(charityId).size();
    Assertions.assertSame(3, productsCount);
  }

  @Test
  void test_get_products() {
    String charityId = charity.getId();

    var productsCount = productService.getProductsForCharity(charityId).size();
    Assertions.assertSame(4, productsCount);

    charity.products.add(productEntities.get(1));
    charityEventRepository.save(charity);
    productsCount = productService.getProductsForCharity(charityId).size();
    Assertions.assertSame(5, productsCount);

    charity.products.remove(0);
    charityEventRepository.save(charity);
    productsCount = productService.getProductsForCharity(charityId).size();
    Assertions.assertSame(4, productsCount);
  }
}
