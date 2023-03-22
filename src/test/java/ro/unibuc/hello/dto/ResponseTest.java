package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResponseTest {
  Response<String> res = new Response<String>("message", "some data");

  @Test
  void test_message() {
    Assertions.assertSame("message", res.getMessage());
  }

  @Test
  void test_data() {
    Assertions.assertSame("some data", res.getData());
  }
}
