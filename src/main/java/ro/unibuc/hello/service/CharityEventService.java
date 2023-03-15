package ro.unibuc.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.unibuc.hello.data.CharityEventEntity;
import ro.unibuc.hello.data.CharityEventRepository;
import ro.unibuc.hello.data.DoneeProductEntity;
import ro.unibuc.hello.data.DoneeProductsEntity;
import ro.unibuc.hello.dto.AssignProductsDoneeDTO;
import ro.unibuc.hello.dto.CreateCharityDTO;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class CharityEventService {

  @Autowired
  private CharityEventRepository charityEventRepository;

  public void createCharityEvent(CreateCharityDTO charityData) {
    var newCharity = new CharityEventEntity(
        charityData.name,
        charityData.location,
        charityData.date);

    newCharity.setDonees(new ArrayList<>());
    newCharity.setProducts(new ArrayList<>());
    newCharity.setDoneesProducts(new ArrayList<>());

    charityEventRepository.insert(newCharity);
  }

  // Returning boolean until we decide to handle errors properly.
  public boolean assignProductsToDonee(String charityId, AssignProductsDoneeDTO productsDoneeDTO) {
    var charity = charityEventRepository.findById(charityId);
    if (!charity.isPresent()) {
      return false;
    }

    var products = productsDoneeDTO.products.stream()
      .map(p -> {
        var doneeProduct = new DoneeProductEntity(p.productId, p.quantity);
        return doneeProduct;
      }).collect(Collectors.toList());
  
    var donneProductsEntity = new DoneeProductsEntity(
        productsDoneeDTO.doneeId,
        products);
    
    charity.get().doneesProducts.removeIf(dp -> dp.doneeId == productsDoneeDTO.doneeId);
    charity.get().doneesProducts.add(donneProductsEntity);

    charityEventRepository.save(charity.get());

    return true;
  }
}
