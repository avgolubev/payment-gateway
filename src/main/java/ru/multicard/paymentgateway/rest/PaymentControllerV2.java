
package ru.multicard.paymentgateway.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.multicard.paymentgateway.dto.*;
import ru.multicard.paymentgateway.service.BackOfficeService;

/**
 * Handling of all incoming provider requests.
 */
@Log4j
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_XML_VALUE)
public final class PaymentControllerV2 {

  /**
   * Injected BackOfficeService.
   */
  private final BackOfficeService backOfficeService;

  /**
   * Processing HTTP GET to check/charge account.
   * @param request
   *    parameters to check account
   * @return
   *    result of operation
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Object getCheckAccount(final CommonRequestV2 request) {

    switch (request.getCheck()) {
      case "1":
        return checkAccount(request.createCheckAccountRequest());

      case "0":
        return chargeAccount(request.createChargeAccountRequest());

      default:
        return null;
    }
  }

  /**
   * Processing HTTP POST to heck/charge account.
   * @param request
   *    parameters to check account
   * @return
   *    result of operation
   */
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public Object postCheckAccount(@RequestBody final CommonRequestV2 request) {

    switch (request.getCheck()) {
      case "1":
        return checkAccount(request.createCheckAccountRequest());

      case "0":
        return chargeAccount(request.createChargeAccountRequest());

      default:
        return null;
    }
  }



  private CheckAccountResponse checkAccount(CheckAccountRequest request) {
    log.info(request);
    CheckAccountResponse result = backOfficeService.checkAccount(request);
    log.info(result);
    return result;
  }

  private ChargeAccountResponse chargeAccount(ChargeAccountRequest request) {
    log.info(request);
    ChargeAccountResponse result = backOfficeService.chargeAccount(request);
    log.info(result);
    return result;
  }

}
