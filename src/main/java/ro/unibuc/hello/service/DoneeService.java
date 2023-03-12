package ro.unibuc.hello.service;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.unibuc.hello.data.CharityEventRepository;
import ro.unibuc.hello.data.DoneeEntity;
import ro.unibuc.hello.dto.DoneeDTO;

@Service
public class DoneeService {
  @Autowired
  private CharityEventRepository charityEventRepository;
  
  public void addDoneesToCharity (String charityId, List<DoneeDTO> donees) {
    var charity = charityEventRepository.findById(charityId);
    if (charity.isPresent()) {
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
}
