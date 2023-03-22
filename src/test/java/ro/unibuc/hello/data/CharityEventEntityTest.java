package ro.unibuc.hello.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharityEventEntityTest {
  private CharityEventEntity charityEvent;

  @BeforeEach
  void setUp() {
    charityEvent = new CharityEventEntity("Charity Event", "Bucharest", "2023-03-22");
  }

  @Test
  void testConstructor() {
    Assertions.assertSame("Charity Event", charityEvent.name);
    Assertions.assertSame("Bucharest", charityEvent.location);
    Assertions.assertSame("2023-03-22", charityEvent.date);
    Assertions.assertNull(charityEvent.id);
  }

  @Test
  void testSettersAndGetters() {
    List<DoneeEntity> donees = Arrays.asList(new DoneeEntity("John", "Doe", 9), new DoneeEntity("Jane", "Doe", 9));
    List<ProductEntity> products = Arrays.asList(new ProductEntity("Food", 10), new ProductEntity("Clothes", 5));
    List<DoneeProductsEntity> doneesProducts = Arrays.asList(new DoneeProductsEntity("1", new ArrayList<>()), new DoneeProductsEntity("2", new ArrayList<>()));

    charityEvent.setDonees(donees);
    charityEvent.setProducts(products);
    charityEvent.setDoneesProducts(doneesProducts);
    charityEvent.id = "12345";

    Assertions.assertSame("12345", charityEvent.getId());
    Assertions.assertSame(donees, charityEvent.donees);
    Assertions.assertSame(products, charityEvent.products);
    Assertions.assertSame(doneesProducts, charityEvent.doneesProducts);
  }
}
