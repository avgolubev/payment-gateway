
package ru.multicard.paymentgateway.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.multicard.paymentgateway.BackOfficeService;
import ru.multicard.paymentgateway.dto.CheckRequest;
import ru.multicard.paymentgateway.dto.CheckResponse;

/**
 *
 * @author avgolubev
 */
@Log4j
@RestController
@RequiredArgsConstructor
public class PaymentController {
  
  private final BackOfficeService backOfficeService;

  @RequestMapping(value = "/check", method = RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
  public CheckResponse getCheck(CheckRequest checkRequest) {
    return backOfficeService.checkSum(checkRequest);
  }
  
  @RequestMapping(value = "/check", method = RequestMethod.POST)
  public CheckResponse postCheck(CheckRequest checkRequest) {
    return backOfficeService.checkSum(checkRequest);
  }  

}
