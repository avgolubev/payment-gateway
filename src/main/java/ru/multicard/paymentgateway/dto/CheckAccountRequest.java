
package ru.multicard.paymentgateway.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Object with data to check account.
 */
@ToString
@Getter
@Setter
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
public class CheckAccountRequest {

  /**
   * A sign of the operation: check account - 1.
   */
  private String check;

  /**
   * Md5 signature for verification.
   */
  private String sign;

  /**
   * Account.
   */
  private String number;


  /**
   * All parameters filled in HTTP request.
   * @return
   *    true if all parameters filled
   */
  public final boolean isAllParametersFilled() {

    return check != null && !check.equals("") && sign != null && !sign.equals("")
      && number != null && !number.equals("");
  }

}
