package ro.unibuc.hello.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateCharityDTO {
  public String name;
  public String location;
  public String date;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public CreateCharityDTO(@JsonProperty("name") String name, @JsonProperty("location") String location, @JsonProperty("date") String date) {
    this.name = name;
    this.location = location;
    this.date = date;
  }

  @Override
  public String toString() {
    return String.format("CreateCharity[name=%s, location=%s, date=%s]", name, location, date);
  }
}
