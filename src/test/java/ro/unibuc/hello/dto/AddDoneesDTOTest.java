package ro.unibuc.hello.dto;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddDoneesDTOTest {

    AddDoneesDTO addDoneesDTO = new AddDoneesDTO(
            List.of(
                    new DoneeDTO("John", "Doe", 20),
                    new DoneeDTO("Jane", "Doe", 19),
                    new DoneeDTO("Joe", "Poe", 30))

    );

    @Test
    public void testAddDoneesDTO() {
        Assertions.assertEquals(3, addDoneesDTO.donees.size());
    }

    @Test
    public void testDoneeDTO() {
        Assertions.assertEquals("John", addDoneesDTO.donees.get(0).firstName);
        Assertions.assertEquals("Doe", addDoneesDTO.donees.get(0).lastName);
        Assertions.assertEquals(20, addDoneesDTO.donees.get(0).age);

        Assertions.assertEquals("Jane", addDoneesDTO.donees.get(1).firstName);
        Assertions.assertEquals("Doe", addDoneesDTO.donees.get(1).lastName);
        Assertions.assertEquals(19, addDoneesDTO.donees.get(1).age);

        Assertions.assertEquals("Joe", addDoneesDTO.donees.get(2).firstName);
        Assertions.assertEquals("Poe", addDoneesDTO.donees.get(2).lastName);
        Assertions.assertEquals(30, addDoneesDTO.donees.get(2).age);
    }

}
