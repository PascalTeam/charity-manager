package ro.unibuc.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.AddProductsDTO;
import ro.unibuc.hello.dto.Response;
import ro.unibuc.hello.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
  @Autowired
  private ProductService productService;

  @PostMapping("/{charityId}")
  @ResponseBody
  public ResponseEntity<Response<?>> addProductToCharity(@PathVariable String charityId, @RequestBody AddProductsDTO productsDTO) {
    productService.addProductsToCharity(charityId, productsDTO.products);

    return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>("Products successfully added to the charity event.", null));
  }

  @DeleteMapping("/{charityId}/{productId}")
  @ResponseBody
  public ResponseEntity<Response<?>> deleteProductFromCharity(@PathVariable String charityId, @PathVariable String productId) {
    productService.deleteProductFromCharity(charityId, productId);

    return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Product successfully deleted from the charity event.", null));
}

  @GetMapping("/{charityId}")
  @ResponseBody
  public ResponseEntity<Response<?>> getProductsForCharity(@PathVariable String charityId) {
    var products = productService.getProductsForCharity(charityId);

    return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Products successfully retrieved.", products));
  }
}
