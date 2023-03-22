package ro.unibuc.hello.data;

import org.springframework.data.annotation.Id;

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
      int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return String.format(
        "Donee[id='%s', firstName='%s', lastName='%s', age='%s']",
        id, firstName, lastName, age);
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }
}
