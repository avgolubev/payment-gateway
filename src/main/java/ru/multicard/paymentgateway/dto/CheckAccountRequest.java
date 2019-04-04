
package ru.multicard.paymentgateway.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Object with data to check account
 */
@ToString
@Getter
@Setter
@XmlRootElement(name="request")
@AllArgsConstructor
@NoArgsConstructor
public class CheckAccountRequest {

  /**
   * a sign of the operation: check account - 1
   */
  private String check;

  /**
   * md5 signature for verification
   */
  private String sign;

  /**
   * account
   */
  private String number;


  /**
   * All parameters filled in HTTP request
   */
  public boolean isAllParametersFilled() {

    return check != null && !check.equals("") && sign != null && !sign.equals("")
      && number != null && !number.equals("");
  }
    
}
