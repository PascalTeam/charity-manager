package ro.unibuc.hello.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoneeEntityTest {
  DoneeEntity donee = new DoneeEntity("fName", "lName", 9);

  @Test
  void test_first_name() {
    Assertions.assertSame("fName", donee.getFirstName());
  }

  @Test
  void test_last_name() {
    Assertions.assertSame("lName", donee.getLastName());
  }
  
  @Test
  void test_age() {
    Assertions.assertSame(9, donee.getAge());
  }
}
