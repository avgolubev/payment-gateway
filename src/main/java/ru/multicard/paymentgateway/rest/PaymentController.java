
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
 * Handling of all incoming provider requests.
 */
@Log4j
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8")
public final class PaymentController {

  /**
   * Injected BackOfficeService.
   */
  private final BackOfficeService backOfficeService;

  /**
   * Processing HTTP GET to check account.
   * @param request
   *    parameters to check account
   * @return
   *    result of operation
   */
  @RequestMapping(value = "/check", method = RequestMethod.GET)
  public CheckAccountResponse getCheckAccount(final CheckAccountRequest request) {
    return checkAccount(request);
  }

  /**
   * Processing HTTP POST to check account.
   * @param request
   *    parameters to check account
   * @return
   *    result of operation
   */
  @RequestMapping(value = "/check", method = RequestMethod.POST)
  public CheckAccountResponse postCheckAccount(@RequestBody final CheckAccountRequest request) {
    return checkAccount(request);
  }

  /**
   * Processing HTTP GET to charge account.
   * @param request
   *    parameters to charge account
   * @return
   *    result of operation
   */
  @RequestMapping(value = "/charge", method = RequestMethod.GET)
  public ChargeAccountResponse getChargeAccount(final ChargeAccountRequest request) {
    return chargeAccount(request);
  }

  /**
   * Processing HTTP POST to charge account.
   * @param request
   *    parameters to charge account
   * @return
   *    result of operation
   */
  @RequestMapping(value = "/charge", method = RequestMethod.POST)
  public ChargeAccountResponse postChargeAccount(@RequestBody final ChargeAccountRequest request) {
    return chargeAccount(request);
  }

  private CheckAccountResponse checkAccount(final CheckAccountRequest request) {
    log.info(request);
    CheckAccountResponse result = backOfficeService.checkAccount(request);
    log.info(result);
    return result;
  }

  private ChargeAccountResponse chargeAccount(final ChargeAccountRequest request) {
    log.info(request);
    ChargeAccountResponse result = backOfficeService.chargeAccount(request);
    log.info(result);
    return result;
  }

}
