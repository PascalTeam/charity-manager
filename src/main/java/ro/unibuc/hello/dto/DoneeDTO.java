package ro.unibuc.hello.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DoneeDTO {

  public String firstName;
  public String lastName;
  public int age;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public DoneeDTO(
      @JsonProperty("firstName") String firstName,
      @JsonProperty("lastName") String lastName,
      @JsonProperty("age") int age
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  @Override
  public String toString() {
    return String.format(
        "Donee[firstName='%s', lastName='%s', age='%s']",
        firstName, lastName, age);
  }
}
