package ro.unibuc.hello.data;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DoneeEntity {
  @Id
  public String id;

  public String firstName;
  public String lastName;
  public int age;

  public DoneeEntity() {
  }

  public DoneeEntity(
      String firstName,
      String lastName,
      int age
    ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  @Override
  public String toString() {
    return String.format(
        "Donee[id='%s', firstName='%s', lastName='%s', age='%s']",
        id, firstName, lastName, age);
  }
}
