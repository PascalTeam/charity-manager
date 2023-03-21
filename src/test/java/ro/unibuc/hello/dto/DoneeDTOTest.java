package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoneeDTOTest {

    DoneeDTO doneeDTO = new DoneeDTO("John", "Doe", 20);

    @Test
    public void testDoneeDTO() {
        Assertions.assertEquals("John", doneeDTO.firstName);
        Assertions.assertEquals("Doe", doneeDTO.lastName);
        Assertions.assertEquals(20, doneeDTO.age);
    }

}
