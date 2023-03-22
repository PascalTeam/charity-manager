package ro.unibuc.hello.dto;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ro.unibuc.hello.data.DoneeEntity;
import ro.unibuc.hello.data.ProductEntity;

public class AssignProductsDoneeDTOTest {

    DoneeEntity doneeEntity = new DoneeEntity("1", "John", "Doe", 20);

    ProductEntity productEntity = new ProductEntity("1", "Milk", 4);
    ProductEntity productEntity2 = new ProductEntity("2", "Bread", 10);
    ProductEntity productEntity3 = new ProductEntity("3", "Eggs", 12);

    AssignProductsDoneeDTO assignProductsDoneeDTO = new AssignProductsDoneeDTO(
            doneeEntity.id,
            List.of(
                    new DoneeProductDTO("1", productEntity.quantity),
                    new DoneeProductDTO("2", productEntity2.quantity),
                    new DoneeProductDTO("3", productEntity3.quantity)));

    @Test
    public void testAssignProductsDoneeDTO() {
        Assertions.assertEquals("1", assignProductsDoneeDTO.doneeId);
        Assertions.assertEquals(3, assignProductsDoneeDTO.products.size());
    }

    @Test
    public void testDoneeProductDTO() {
        Assertions.assertEquals("1", assignProductsDoneeDTO.products.get(0).productId);
        Assertions.assertEquals(4, assignProductsDoneeDTO.products.get(0).quantity);

        Assertions.assertEquals("2", assignProductsDoneeDTO.products.get(1).productId);
        Assertions.assertEquals(10, assignProductsDoneeDTO.products.get(1).quantity);

        Assertions.assertEquals("3", assignProductsDoneeDTO.products.get(2).productId);
        Assertions.assertEquals(12, assignProductsDoneeDTO.products.get(2).quantity);
    }

}
