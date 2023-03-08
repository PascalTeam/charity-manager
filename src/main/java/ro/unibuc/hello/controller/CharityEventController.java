package ro.unibuc.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.unibuc.hello.dto.CreateCharityDTO;
import ro.unibuc.hello.dto.Response;
import ro.unibuc.hello.service.CharityEventService;

@Controller
@RequestMapping("/charity/")
public class CharityEventController {

  @Autowired
  private CharityEventService charityEventService;

  @PostMapping
  @ResponseBody
  public ResponseEntity<Response> createCharity (@RequestBody CreateCharityDTO charityData) {
    charityEventService.createCharityEvent(charityData);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>("Charity event successfully created", null));
  }
}
