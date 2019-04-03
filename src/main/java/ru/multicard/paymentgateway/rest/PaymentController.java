
package ru.multicard.paymentgateway.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.multicard.paymentgateway.service.BackOfficeService;
import ru.multicard.paymentgateway.dto.CheckContractNumberRequest;
import ru.multicard.paymentgateway.dto.CheckAccountResponse;

/**
 * Handling of all incoming provider requests
 */
@Log4j
@RestController
@RequiredArgsConstructor
public class PaymentController {
  
  private final BackOfficeService backOfficeService;

  @RequestMapping(value = "/check", method = RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
  public CheckAccountResponse getCheck(CheckContractNumberRequest checkContractNumberRequest) {
    return backOfficeService.checkAccount(checkContractNumberRequest);
  }
  
  @RequestMapping(value = "/check", method = RequestMethod.POST)
  public CheckAccountResponse postCheck(CheckContractNumberRequest checkContractNumberRequest) {
    return backOfficeService.checkAccount(checkContractNumberRequest);
  }

  @RequestMapping(value = "/charge", method = RequestMethod.GET)
  public void getCharge() {

  }

  @RequestMapping(value = "/charge", method = RequestMethod.POST)
  public void postCharge() {

  }

}
