package ro.unibuc.hello.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.unibuc.hello.data.CharityEventEntity;
import ro.unibuc.hello.data.CharityEventRepository;
import ro.unibuc.hello.dto.CreateCharityDTO;

@Component
public class CharityEventService {

  @Autowired
  private CharityEventRepository charityEventRepository;

  public void createCharityEvent (CreateCharityDTO charityData) {
    var newCharity = new CharityEventEntity(
      charityData.name,
      charityData.location,
      charityData.date
    );

    newCharity.setDonees(new ArrayList<>());

    charityEventRepository.insert(newCharity);
  }
}
