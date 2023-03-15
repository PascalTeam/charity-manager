package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.hello.data.CharityEventRepository;
import ro.unibuc.hello.data.ProductEntity;
import ro.unibuc.hello.dto.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
  @Autowired
  private CharityEventRepository charityEventRepository;

  public void addProductsToCharity(String charityId, List<ProductDTO> products) {
    var charity = charityEventRepository.findById(charityId);
    if (charity.isEmpty()) {
      return;
    }

    var productEntities = products.stream()
        .map(productDTO -> {
          var productEntity = new ProductEntity(productDTO.name, productDTO.quantity);

          var id = new ObjectId();
          productEntity.setId(id.toString());

          return productEntity;
        })
        .collect(Collectors.toList());

    charity.get().products.addAll(productEntities);

    charityEventRepository.save(charity.get());
  }

  public void deleteProductFromCharity(String charityId, String productId) {
    var charity = charityEventRepository.findById(charityId);
    if (charity.isEmpty()) {
      return;
    }

    charity.get().products.removeIf(product -> product.id.equals(productId));

    charityEventRepository.save(charity.get());
}

  public List<ProductDTO> getProductsForCharity(String charityId) {
    var charity = charityEventRepository.findById(charityId);
    return charity.map(charityEventEntity -> charityEventEntity.products.stream()
        .map(productEntity -> new ProductDTO(productEntity.name, productEntity.quantity))
        .collect(Collectors.toList())).orElse(null);
  }
}
