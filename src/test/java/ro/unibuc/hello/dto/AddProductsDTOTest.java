package ro.unibuc.hello.dto;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddProductsDTOTest {

    AddProductsDTO addProductsDTO = new AddProductsDTO(
            List.of(new ProductDTO("Milk", 4),
                    new ProductDTO("Bread", 10),
                    new ProductDTO("Eggs", 12)));

    @Test
    public void testAddProductsDTO() {
        Assertions.assertEquals(3, addProductsDTO.products.size());
    }

    @Test
    public void testProductDTO() {
        Assertions.assertEquals("Milk", addProductsDTO.products.get(0).name);
        Assertions.assertEquals(4, addProductsDTO.products.get(0).quantity);

        Assertions.assertEquals("Bread", addProductsDTO.products.get(1).name);
        Assertions.assertEquals(10, addProductsDTO.products.get(1).quantity);

        Assertions.assertEquals("Eggs", addProductsDTO.products.get(2).name);
        Assertions.assertEquals(12, addProductsDTO.products.get(2).quantity);

    }

}
