
package ru.multicard.paymentgateway.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

import org.springframework.http.MediaType;
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
@RequestMapping(produces = MediaType.APPLICATION_XML_VALUE)
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
  public CheckAccountResponse getCheck(final CheckAccountRequest request) {
    log.info(request);
    return backOfficeService.checkAccount(request);
  }

  /**
   * Processing HTTP POST to check account.
   * @param request
   *    parameters to check account
   * @return
   *    result of operation
   */
  @RequestMapping(value = "/check", method = RequestMethod.POST)
  public CheckAccountResponse postCheck(final CheckAccountRequest request) {
    log.info(request);
    return backOfficeService.checkAccount(request);
  }

  /**
   * Processing HTTP GET to charge account.
   * @param request
   *    parameters to charge account
   * @return
   *    result of operation
   */
  @RequestMapping(value = "/charge", method = RequestMethod.GET)
  public ChargeAccountResponse getCharge(final ChargeAccountRequest request) {
    log.info(request);
    return backOfficeService.chargeAccount(request);
  }

  /**
   * Processing HTTP POST to charge account.
   * @param request
   *    parameters to charge account
   * @return
   *    result of operation
   */
  @RequestMapping(value = "/charge", method = RequestMethod.POST)
  public ChargeAccountResponse postCharge(final ChargeAccountRequest request) {
    log.info(request);
    return backOfficeService.chargeAccount(request);
  }

}
