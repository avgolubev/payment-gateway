
package ru.multicard.paymentgateway.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1
 */
@RestController
public class PaymentController {

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public String getString() {
    return "34343";
  }

}
