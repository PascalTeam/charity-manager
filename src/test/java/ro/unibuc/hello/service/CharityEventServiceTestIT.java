package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.CharityEventEntity;
import ro.unibuc.hello.data.CharityEventRepository;
import ro.unibuc.hello.data.ProductEntity;
import ro.unibuc.hello.dto.CreateCharityDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Tag("IT")
public class CharityEventServiceTestIT {
  @Autowired
  CharityEventService charityEventService;

  @Autowired
  CharityEventRepository charityEventRepository;

  CharityEventEntity charity;
  List<ProductEntity> products = new ArrayList<>();

  @BeforeEach
  public void init() {
    var charityDTO = new CreateCharityDTO("Name", "Location", "29/03/2023");
    charity = charityEventService.createCharityEvent(charityDTO);

    // Add products to the charity event
    IntStream.range(1, 5)
        .forEach(i -> {
          var product = new ProductEntity("Product #"+i, i + 10);

          var id = new ObjectId();
          product.setId(id.toString());

          products.add(product);
        });
    charity.setProducts(products);
    charityEventRepository.save(charity);
  }

  @AfterEach
  public void teardown() {
    charityEventRepository.deleteAll();
  }

  @Test
  void test_createCharityEvent() {
    // Arrange
    var createCharityDTO = new CreateCharityDTO("Test Name", "Test Location", "25/05/2025");

    // Act
    var charity = charityEventService.createCharityEvent(createCharityDTO);

    // Assert
    Assertions.assertNotNull(charity);
    Assertions.assertEquals(createCharityDTO.getName(), charity.name);
    Assertions.assertEquals(createCharityDTO.getLocation(), charity.location);
    Assertions.assertEquals(createCharityDTO.getDate(), charity.date);
  }

  @Test
  void test_getCharityEvent(){
    List<CharityEventEntity> charityEvents = charityEventService.getCharityEvents();

    Assertions.assertFalse(charityEvents.isEmpty());
  }
}
