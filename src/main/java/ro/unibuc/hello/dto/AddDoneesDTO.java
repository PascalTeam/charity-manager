package ro.unibuc.hello.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddDoneesDTO {
  public List<DoneeDTO> donees;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public AddDoneesDTO(@JsonProperty("donees") List<DoneeDTO> donees) {
    this.donees = donees;
  }

  @Override
  public String toString() {
    return donees.toString();
  }
}
