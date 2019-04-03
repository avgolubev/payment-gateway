
package ru.multicard.paymentgateway;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.multicard.paymentgateway.dto.CheckRequest;
import ru.multicard.paymentgateway.dto.CheckResponse;

/**
 *
 * @author AGolubev
 */
@Log4j
@Service
public class BackOfficeService {


  public CheckResponse checkSum(CheckRequest checkRequest) {
    CheckResponse s = new CheckResponse();
    s.setCheck(2323);
    return s;

  }

}
