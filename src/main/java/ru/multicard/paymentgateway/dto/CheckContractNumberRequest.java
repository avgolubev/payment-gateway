
package ru.multicard.paymentgateway.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The incoming request object inspection contract number
 */
@Getter
@Setter
@XmlRootElement(name="request")
public class CheckContractNumberRequest {

  /**
   * a sign of the operation: account replenishment - 0
   */
  private int check;

  /**
   * md5 signature for verification
   */
  private String sign;

  /**
   * account
   */
  private String number;
    
}
