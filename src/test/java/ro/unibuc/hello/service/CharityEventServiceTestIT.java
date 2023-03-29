package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.CharityEventEntity;
import ro.unibuc.hello.data.CharityEventRepository;
import ro.unibuc.hello.data.ProductEntity;
import ro.unibuc.hello.dto.AssignProductsDoneeDTO;
import ro.unibuc.hello.dto.CreateCharityDTO;
import ro.unibuc.hello.dto.DoneeProductDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@Tag("IT")
public class CharityEventServiceTestIT {
  @Autowired
  CharityEventService charityEventService;

  @Autowired
  CharityEventRepository charityEventRepository;

  CharityEventEntity charityEventEntity;
  List<ProductEntity> productEntities = new ArrayList<>();

  @BeforeEach
  public void init() {
    var charityDTO = new CreateCharityDTO("Name", "Location", "29/03/2023");
    charityEventEntity = charityEventService.createCharityEvent(charityDTO);

    // Add products to the charity event
    IntStream.range(1, 5)
        .forEach(i -> {
          var product = new ProductEntity("Product #" + i, i + 10);

          var id = new ObjectId();
          product.setId(id.toString());

          productEntities.add(product);
        });
    charityEventEntity.setProducts(productEntities);
    charityEventRepository.save(charityEventEntity);
  }

  @AfterEach
  public void teardown() {
    charityEventRepository.deleteAll();
  }

  @Test
  void test_createCharityEvent() {
    var createCharityDTO = new CreateCharityDTO("Test Name", "Test Location", "25/05/2025");

    var charity = charityEventService.createCharityEvent(createCharityDTO);

    Assertions.assertNotNull(charity);
    Assertions.assertEquals(createCharityDTO.getName(), charity.name);
    Assertions.assertEquals(createCharityDTO.getLocation(), charity.location);
    Assertions.assertEquals(createCharityDTO.getDate(), charity.date);
  }

  @Test
  void test_getCharityEvent() {
    List<CharityEventEntity> charityEvents = charityEventService.getCharityEvents();

    Assertions.assertFalse(charityEvents.isEmpty());
  }

  @Test
  void test_assignProductsToDonee() {
    var charityId = charityEventEntity.getId();
    var DONEE_ID = "ID";

    var productsToAssign = productEntities.stream()
        .map(p -> new DoneeProductDTO(p.id, p.quantity))
        .collect(Collectors.toList());
    AssignProductsDoneeDTO assignProductsDoneeDTO = new AssignProductsDoneeDTO(DONEE_ID, productsToAssign);

    boolean result = charityEventService.assignProductsToDonee(charityId, assignProductsDoneeDTO);
    Assertions.assertTrue(result);

    Optional<List<ProductEntity>> doneeProducts = charityEventService.getDoneeProducts(charityId, DONEE_ID);
    Assertions.assertTrue(doneeProducts.isPresent());
    Assertions.assertEquals(assignProductsDoneeDTO.products.size(), doneeProducts.get().size());
  }
}
