package ru.multicard.paymentgateway.service;


import lombok.NoArgsConstructor;
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
import static ru.multicard.paymentgateway.service.OperationError.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@NoArgsConstructor
public class BackOfficeServiceTest {

  @Autowired
  /**
   * Injected service
   */
  private BackOfficeService backOfficeService;

  /**
   * Secret signing key from application.properties.
   */
  @Value("${salt}")
  private String salt;

  @Test
  @DisplayName("Test validation in BackOfficeService.checkAccount.")
  public void checkAccountNotAllParametersFilled() {
    final CheckAccountRequest request = new CheckAccountRequest("1", "", "123");
    assertEquals(ABSENT_PARAMETER.getCode(), backOfficeService.checkAccount(request).getRetval(),
      "A result with error ABSENT_PARAMETER is returned, if at least one query parameter is missing.");

    request.setSign("xx");
    assertEquals(MD5_ERROR.getCode(), backOfficeService.checkAccount(request).getRetval(),
      "A result with error MD5_ERROR is returned, if parameter sign has invalid md5 hash.");

    request.setSign(md5Hash(request.getNumber()));
    assertEquals(NO_ERROR.getCode(), backOfficeService.checkAccount(request).getRetval(),
      "A result with error NO_ERROR is returned, if nothing errors in operation.");
  }

  @Test
  @DisplayName("Test validation in BackOfficeService.chargeAccount.")
  public void chargeAccount() {
    final ChargeAccountRequest request = new ChargeAccountRequest("0", "", "123", "12,00",
      "xxxx", ".13.2019");
    assertEquals(ABSENT_PARAMETER.getCode(), backOfficeService.chargeAccount(request).getRetval(),
      "A result with error ABSENT_PARAMETER is returned, if at least one query parameter is missing.");

    request.setSign("xxx");
    assertEquals(MD5_ERROR.getCode(), backOfficeService.chargeAccount(request).getRetval(),
      "A result with error MD5_ERROR is returned, if parameter sign has invalid md5 hash.");

    request.setSign(md5Hash(request.getNumber()));
    assertEquals(INVALID_AMOUNT_FORMAT.getCode(), backOfficeService.chargeAccount(request).getRetval(),
      "A result with error INVALID_AMOUNT_FORMAT is returned, if parameter amount has invalid format.");

    request.setAmount("1200.78");
    assertEquals(INVALID_PAYMENT_DATE_FORMAT.getCode(), backOfficeService.chargeAccount(request).getRetval(),
      "A result with error INVALID_PAYMENT_DATE_FORMAT is returned, if parameter paymentCreate has invalid format.");

    request.setPaymentCreate("12.03.2019 12:34:00");
    assertEquals(NO_ERROR.getCode(), backOfficeService.chargeAccount(request).getRetval(),
      "A result with error NO_ERROR is returned, if nothing errors in operation.");

  }

  private String md5Hash(final String value) {
    return DigestUtils.md5Hex(value + salt);
  }

}