package ro.unibuc.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.AddDoneesDTO;
import ro.unibuc.hello.dto.Response;
import ro.unibuc.hello.service.DoneeService;

@Controller
@RequestMapping("/donee/")
public class DoneeController {

  @Autowired
  private DoneeService doneeService;

  @PostMapping("/{charityId}")
  @ResponseBody
  public ResponseEntity<Response<?>> addDoneesToCharity(@PathVariable String charityId, @RequestBody AddDoneesDTO doneesDTO) {
    doneeService.addDoneesToCharity(charityId, doneesDTO.donees);

    return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>("Donees successfully added to the charity event.", null));
  }
}
