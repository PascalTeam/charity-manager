package ro.unibuc.hello.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ro.unibuc.hello.data.CharityEventEntity;
import ro.unibuc.hello.data.CharityEventRepository;
import ro.unibuc.hello.data.DoneeEntity;
import ro.unibuc.hello.dto.DoneeDTO;

@SpringBootTest
@Tag("IT")
public class DoneeServiceIT {
  @Autowired
  CharityEventRepository charityEventRepository;

  @Autowired
  DoneeService donneService;

  CharityEventEntity charity;
  List<DoneeEntity> doneeEntities = new ArrayList<>();

  @BeforeEach
  public void init() {
    var newCharity = new CharityEventEntity(
        "ch",
        "loc",
        "22-02-2022");
    newCharity.setDonees(new ArrayList<>());

    charity = charityEventRepository.insert(newCharity);

    IntStream.range(1, 5)
        .forEach(i -> {
          var doneeEntity = new DoneeEntity(
              String.format("firstName #%s", i),
              String.format("lastName #%s", i),
              5 + i);
          var id = new ObjectId();
          doneeEntity.setId(id.toString());

          doneeEntities.add(doneeEntity);
        });
    charity.donees.addAll(doneeEntities);

    charityEventRepository.save(charity);
  }

  @AfterEach
  public void teardown() {
    charityEventRepository.deleteAll();
  }

  @Test
  void test_get_donee() {
    String charityId = charity.getId();
    String doneeId = doneeEntities.get(0).getId();

    var result = donneService.getDoneeFromCharity(charityId, "nonExistentDoneeId");
    Assertions.assertNull(result);

    result = donneService.getDoneeFromCharity("nonExistentCharityId", doneeId);
    Assertions.assertNull(result);

    result = donneService.getDoneeFromCharity(charityId, doneeId);
    Assertions.assertNotNull(result);
    Assertions.assertEquals("firstName #1", result.getFirstName());
    Assertions.assertEquals("lastName #1", result.getLastName());
    Assertions.assertSame(6, result.getAge());
  }

  @Test
  void test_update_donee() {
    String charityId = charity.getId();
    String doneeId = doneeEntities.get(0).getId();

    var updatedDonee = new DoneeDTO("updatedF", "updatedL", 99);
    donneService.updateDoneeFromCharity(charityId, doneeId, updatedDonee);
    var doneeToBeUpdated = charityEventRepository.findById(charityId).get().donees.stream()
        .filter(d -> d.id.equals(doneeId))
        .findFirst()
        .get();
    Assertions.assertNotNull(doneeToBeUpdated);
    Assertions.assertEquals("updatedF", doneeToBeUpdated.getFirstName());
    Assertions.assertEquals("updatedL", doneeToBeUpdated.getLastName());
    Assertions.assertSame(99, doneeToBeUpdated.getAge());
  }
}