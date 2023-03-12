package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.hello.data.CharityEventRepository;
import ro.unibuc.hello.data.DoneeEntity;
import ro.unibuc.hello.dto.DoneeDTO;

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
              d.age
          );

          var id = new ObjectId();
          doneeEntity.setId(id.toString());

          return doneeEntity;
        })
        .collect(Collectors.toList());

    charity.get().donees.addAll(doneesEntities);

    charityEventRepository.save(charity.get());
  }
}