
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
@RequestMapping(produces = MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8")
public final class PaymentControllerV2 {

  /**
   * Injected BackOfficeService.
   */
  private final BackOfficeService backOfficeService;

  /**
   * Processing HTTP GET to check/charge account.
   * @param request
   *    parameters to check/charge account
   * @return
   *    result of operation
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Object getOperationAccount(final CommonRequestV2 request) {
    return operationAccount(request);
  }

  /**
   * Processing HTTP POST to heck/charge account.
   * @param request
   *    parameters to check/charge account
   * @return
   *    result of operation
   */
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public Object postOperationAccount(@RequestBody final CommonRequestV2 request) {
    return operationAccount(request);
  }

  /**
   * Choosing operation.
   * @param request
   *    parameters to check/charge account
   * @return
   *    result of operation
   */
  private Object operationAccount(final CommonRequestV2 request) {

    log.info(request);
    Object result;

    switch (request.getCheck()) {
      case "1":
        result = backOfficeService.checkAccount(request.createCheckAccountRequest());
        break;

      case "0":
        result = backOfficeService.chargeAccount(request.createChargeAccountRequest());
        break;

      default:
        result = null;
    }

    log.info(result);
    return result;
  }

}
