package ru.multicard.paymentgateway.service;


import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.multicard.paymentgateway.dto.ChargeAccountRequest;
import ru.multicard.paymentgateway.dto.CheckAccountRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackOfficeServiceTest {

  @Autowired
  private BackOfficeService backOfficeService;

  @Value("${salt}")
  private String salt;

  @Test
  @DisplayName("Test validation in BackOfficeService.checkAccount")
  void checkAccountNotAllParametersFilled() {
    CheckAccountRequest request = new CheckAccountRequest("1", "", "123");
    assertEquals(OperationError.ABSENT_PARAMETER.getCode(), backOfficeService.checkAccount(request).getRetval());

    request.setSign("xx");
    assertEquals(OperationError.MD5_ERROR.getCode(), backOfficeService.checkAccount(request).getRetval());

    request.setSign(md5Hash(request.getNumber()));
    assertEquals(OperationError.NO_ERROR.getCode(), backOfficeService.checkAccount(request).getRetval());
  }

  @Test
  @DisplayName("Test validation in BackOfficeService.chargeAccount")
  void chargeAccount() {
    ChargeAccountRequest request = new ChargeAccountRequest("0", "", "123", "12,00",
      "xxxx", ".13.2019");
    assertEquals(OperationError.ABSENT_PARAMETER.getCode(), backOfficeService.chargeAccount(request).getRetval());

    request.setSign("xxx");
    assertEquals(OperationError.MD5_ERROR.getCode(), backOfficeService.chargeAccount(request).getRetval());

    request.setSign(md5Hash(request.getNumber()));
    assertEquals(OperationError.INVALID_AMOUNT_FORMAT.getCode(), backOfficeService.chargeAccount(request).getRetval());

    request.setAmount("1200.78");
    assertEquals(OperationError.INVALID_PAYMENT_DATE_FORMAT.getCode(), backOfficeService.chargeAccount(request).getRetval());

    request.setPaymentCreate("12.03.2019 12:34:00");
    assertEquals(OperationError.NO_ERROR.getCode(), backOfficeService.chargeAccount(request).getRetval());

  }

  private String md5Hash(String value) {
    return DigestUtils.md5Hex(value + salt);
  }

}