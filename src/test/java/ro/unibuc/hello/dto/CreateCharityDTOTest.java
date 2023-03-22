package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateCharityDTOTest {
  CreateCharityDTO charity = new CreateCharityDTO("Event#1", "Location#2", "19-04-2023");

  @Test
  void test_event_name() {
    Assertions.assertSame("Event#1", charity.getName());
  }

  @Test
  void test_location() {
    Assertions.assertSame("Location#2", charity.getLocation());
  }

  @Test
  void test_date() {
    Assertions.assertSame("19-04-2023", charity.getDate());
  }
}
