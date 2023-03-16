package ro.unibuc.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ro.unibuc.hello.data.DoneeEntity;
import ro.unibuc.hello.dto.AddDoneesDTO;
import ro.unibuc.hello.dto.DoneeDTO;
import ro.unibuc.hello.dto.Response;
import ro.unibuc.hello.service.DoneeService;

@Controller
@RequestMapping("/donee/")
public class DoneeController {

  @Autowired
  private DoneeService doneeService;

  @PostMapping("/{charityId}")
  @ResponseBody
  public ResponseEntity<Response<?>> addDoneesToCharity(@PathVariable String charityId,
      @RequestBody AddDoneesDTO doneesDTO) {
    doneeService.addDoneesToCharity(charityId, doneesDTO.donees);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new Response<>("Donees successfully added to the charity event.", null));
  }

  @GetMapping("/charity/{charityId}")
  public ResponseEntity<Response<?>> getDoneesFromCharity(@PathVariable String charityId) {
    List<DoneeEntity> donees = doneeService.getDoneesFromCharity(charityId);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new Response<>("The donees have been fetched successfully.", donees));
  }

  // Get one donee from a charity event
  @GetMapping("/charity/{charityId}/{doneeId}")
  public ResponseEntity<Response<?>> getDoneeFromCharity(@PathVariable String charityId, @PathVariable String doneeId) {
    DoneeEntity donee = doneeService.getDoneeFromCharity(charityId, doneeId);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new Response<>("The donee has been fetched successfully.", donee));
  }

  // Patch one donee from a charity event
  @PatchMapping("/charity/{charityId}/{doneeId}")
  public ResponseEntity<Response<?>> updateDoneeFromCharity(@PathVariable String charityId,
      @PathVariable String doneeId,
      @RequestBody DoneeDTO donee) {
    doneeService.updateDoneeFromCharity(charityId, doneeId, donee);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new Response<>("The donee has been updated successfully.", null));
  }

}
