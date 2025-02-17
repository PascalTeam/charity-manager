package ro.unibuc.hello.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.unibuc.hello.data.CharityEventEntity;

import ro.unibuc.hello.dto.AssignProductsDoneeDTO;
import ro.unibuc.hello.dto.CreateCharityDTO;
import ro.unibuc.hello.dto.Response;
import ro.unibuc.hello.service.CharityEventService;

@Controller
@RequestMapping("/charity/")
public class CharityEventController {

  @Autowired
  private CharityEventService charityEventService;

  @GetMapping
  @ResponseBody
  public ResponseEntity<Response<?>> getCharities() {
    var charities = charityEventService.getCharityEvents();
    return ResponseEntity.status(HttpStatus.OK)
        .body(new Response<>("Charity events successfully fetched", charities));
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Response<?>> createCharity(@RequestBody CreateCharityDTO charityData) {
    var charityEvent = charityEventService.createCharityEvent(charityData);

    var responseBody = new HashMap<String, String>();
    responseBody.put("id", charityEvent.getId());

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new Response<>("Charity event successfully created", responseBody));
  }

  @PutMapping("/{charityId}")
  @ResponseBody
  public ResponseEntity<Response<?>> assignProductsToDonee(@PathVariable String charityId,
      @RequestBody AssignProductsDoneeDTO doneeProductsDTO) {
    var isSuccessful = charityEventService.assignProductsToDonee(charityId, doneeProductsDTO);
    if (isSuccessful) {
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(new Response<>("The products have been successfully assigned to the donee.", null));
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new Response<>("An error occurred while adding the products to the donee", null));
  }

  @GetMapping("/{charityId}/donee/{doneeId}/products")
  public ResponseEntity<Response<?>> assignProductsToDonee(@PathVariable String charityId,
      @PathVariable String doneeId) {
    var products = charityEventService.getDoneeProducts(charityId, doneeId);
    if (products.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(new Response<>("The donee's products have been successfully fetched.", products.get()));
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new Response<>("An error occurred while fetching the donee's product", null));
  }

}
