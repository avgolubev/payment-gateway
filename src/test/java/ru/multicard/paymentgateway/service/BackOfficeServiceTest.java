
package ru.multicard.paymentgateway.service;


import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.multicard.paymentgateway.dto.ChargeAccountRequest;
import ru.multicard.paymentgateway.dto.CheckAccountRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.multicard.paymentgateway.service.OperationError.*;

@RunWith(JUnitPlatform.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
@NoArgsConstructor
@DisplayName("Test BackOfficeService functionality.")
public class BackOfficeServiceTest {

  @Autowired
  /**
   * Injected service
   */
  private BackOfficeService backOfficeService;

  @Test
  @DisplayName("Test validation in BackOfficeService.checkAccount.")
  public void checkAccount(@Value("${salt}") final String salt) {
    final CheckAccountRequest request = new CheckAccountRequest("1", "", "123");
    assertEquals(ABSENT_PARAMETER.getCode(), backOfficeService.checkAccount(request).getRetval(),
      "Error should be ABSENT_PARAMETER when at least one query parameter is missing.");

    request.setSign("xx");
    assertEquals(MD5_ERROR.getCode(), backOfficeService.checkAccount(request).getRetval(),
      "Error should be MD5_ERROR when sign parameter is invalid md5 hash.");

    request.setSign(md5Hash(request.getNumber(), salt));
    assertEquals(NO_ERROR.getCode(), backOfficeService.checkAccount(request).getRetval(),
      "Error should be NO_ERROR  when nothing errors in operation.");
  }

  @Test
  @DisplayName("Test validation in BackOfficeService.chargeAccount.")
  public void chargeAccount(@Value("${salt}") final String salt) {
    final ChargeAccountRequest request = new ChargeAccountRequest("0", "", "123", "12,00",
      "xxxx", ".13.2019");
    assertEquals(ABSENT_PARAMETER.getCode(), backOfficeService.chargeAccount(request).getRetval(),
      "Error should be ABSENT_PARAMETER when at least one query parameter is missing.");

    request.setSign("xxx");
    assertEquals(MD5_ERROR.getCode(), backOfficeService.chargeAccount(request).getRetval(),
      "Error should be MD5_ERROR when sign parameter is invalid md5 hash.");

    request.setSign(md5Hash(request.getNumber(), salt));
    assertEquals(INVALID_AMOUNT_FORMAT.getCode(), backOfficeService.chargeAccount(request).getRetval(),
      "Error should be INVALID_AMOUNT_FORMAT when parameter amount has invalid format.");

    request.setAmount("1200.78");
    assertEquals(INVALID_PAYMENT_DATE_FORMAT.getCode(), backOfficeService.chargeAccount(request).getRetval(),
      "Error should be INVALID_PAYMENT_DATE_FORMAT when payment_create parameter has invalid format.");

    request.setPaymentCreate("12.03.2019 12:34:00");
    assertEquals(NO_ERROR.getCode(), backOfficeService.chargeAccount(request).getRetval(),
      "Error should be NO_ERROR when nothing errors in operation.");

  }

  private String md5Hash(final String value, final String salt) {
    return DigestUtils.md5Hex(value + salt);
  }

}