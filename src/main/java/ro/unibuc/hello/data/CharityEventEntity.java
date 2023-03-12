package ro.unibuc.hello.data;

import java.util.List;

import org.springframework.data.annotation.Id;

public class CharityEventEntity {
  @Id
  public String id;

  public String name;
  public String location;
  public String date;

  public List<DoneeEntity> donees;

  public CharityEventEntity() {
  }

  public CharityEventEntity(String name, String location, String date) {
    this.name = name;
    this.location = location;
    this.date = date;
  }

  @Override
  public String toString() {
    return String.format(
        "CharityEvent[id='%s', name='%s', location='%s', date='%s']",
        id, name, location, date);
  }

  public void setDonees(List<DoneeEntity> donees) {
    this.donees = donees;
  }
}
