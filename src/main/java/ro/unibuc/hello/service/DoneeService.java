package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.hello.data.CharityEventRepository;
import ro.unibuc.hello.data.DoneeEntity;
import ro.unibuc.hello.dto.DoneeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoneeService {
  @Autowired
  private CharityEventRepository charityEventRepository;

  public void addDoneesToCharity(String charityId, List<DoneeDTO> donees) {
    var charity = charityEventRepository.findById(charityId);
    if (!charity.isPresent()) {
      return;
    }

    var doneesEntities = donees.stream().map(d -> {
      var doneeEntity = new DoneeEntity(
          d.firstName,
          d.lastName,
          d.age);

      var id = new ObjectId();
      doneeEntity.setId(id.toString());

      return doneeEntity;
    })
        .collect(Collectors.toList());

    charity.get().donees.addAll(doneesEntities);

    charityEventRepository.save(charity.get());
  }

  public List<DoneeEntity> getDoneesFromCharity(String charityId) {
    var charity = charityEventRepository.findById(charityId);
    if (!charity.isPresent()) {
      return new ArrayList<>();
    }

    return charity.get().donees;
  }

  public DoneeEntity getDoneeFromCharity(String charityId, String doneeId) {

    var charity = charityEventRepository.findById(charityId);
    if (!charity.isPresent()) {
      return null;
    }

    return charity.get().donees.stream()
        .filter(donee -> donee.id.equals(doneeId))
        .findFirst()
        .orElse(null);

  }

  public void updateDoneeFromCharity(String charityId, String doneeId, DoneeDTO donee) {
    var charity = charityEventRepository.findById(charityId);
    if (!charity.isPresent()) {
      return;
    }

    var doneeEntity = charity.get().donees.stream()
        .filter(d -> d.id.equals(doneeId))
        .findFirst();

    if (doneeEntity.isPresent()) {
      doneeEntity.get().firstName = donee.firstName;
      doneeEntity.get().lastName = donee.lastName;
      doneeEntity.get().age = donee.age;
      charityEventRepository.save(charity.get());

    }

  }
}