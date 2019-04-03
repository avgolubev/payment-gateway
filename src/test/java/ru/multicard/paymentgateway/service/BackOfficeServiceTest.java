package ru.multicard.paymentgateway.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.multicard.paymentgateway.dto.CheckAccountRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackOfficeServiceTest {

  @Autowired
  private BackOfficeService backOfficeService;

  @Test
  void checkAccountNotAllParametersFilled() {

    CheckAccountRequest request = new CheckAccountRequest("112", "", "123");
    assertEquals(OperationError.ABSENT_PARAMETER.getCode(), backOfficeService.checkAccount(request).getRetval());
  }

  @Test
  void chargeAccount() {
  }
}