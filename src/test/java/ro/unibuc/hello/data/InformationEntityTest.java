package ro.unibuc.hello.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InformationEntityTest {
  InformationEntity inf = new InformationEntity("title", "description");
  
  @Test
  void test_title() {
    Assertions.assertSame("title", inf.getTitle());
  }

  @Test
  void test_description() {
    Assertions.assertSame("description", inf.getDescription());
  }
}
