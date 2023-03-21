package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoneeProductDTOTest {

    DoneeProductDTO doneeProductDTO = new DoneeProductDTO("1", 4);

    @Test
    public void testDoneeProductDTO() {
        Assertions.assertEquals("1", doneeProductDTO.productId);
        Assertions.assertEquals(4, doneeProductDTO.quantity);
    }

}
