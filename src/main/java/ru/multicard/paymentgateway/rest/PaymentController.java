
package ru.multicard.paymentgateway.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.multicard.paymentgateway.dto.ChargeAccountRequest;
import ru.multicard.paymentgateway.dto.ChargeAccountResponse;
import ru.multicard.paymentgateway.service.BackOfficeService;
import ru.multicard.paymentgateway.dto.CheckAccountRequest;
import ru.multicard.paymentgateway.dto.CheckAccountResponse;

/**
 * Handling of all incoming provider requests
 */
@Log4j
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_XML_VALUE)
public final class PaymentController {

  /**
   * Injected BackOfficeService
   */
  private final BackOfficeService backOfficeService;

  /**
   * Processing HTTP GET to check account
   * @param checkAccountRequest
   * @return
   */
  @RequestMapping(value = "/check", method = RequestMethod.GET)
  public CheckAccountResponse getCheck(CheckAccountRequest checkAccountRequest) {
    log.info(checkAccountRequest);
    return backOfficeService.checkAccount(checkAccountRequest);
  }

  /**
   * Processing HTTP POST to check account
   * @param checkAccountRequest
   * @return
   */
  @RequestMapping(value = "/check", method = RequestMethod.POST)
  public CheckAccountResponse postCheck(@RequestBody CheckAccountRequest checkAccountRequest) {
    log.info(checkAccountRequest);
    return backOfficeService.checkAccount(checkAccountRequest);
  }

  /**
   * Processing HTTP GET to charge account
   */
  @RequestMapping(value = "/charge", method = RequestMethod.GET)
  public ChargeAccountResponse getCharge(ChargeAccountRequest chargeAccountRequest) {
    log.info(chargeAccountRequest);
    return backOfficeService.chargeAccount(chargeAccountRequest);
  }

  /**
   * Processing HTTP POST to charge account
   */
  @RequestMapping(value = "/charge", method = RequestMethod.POST)
  public ChargeAccountResponse postCharge(ChargeAccountRequest chargeAccountRequest) {
    log.info(chargeAccountRequest);
    return backOfficeService.chargeAccount(chargeAccountRequest);
  }

}
