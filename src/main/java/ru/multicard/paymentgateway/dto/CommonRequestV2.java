
package ru.multicard.paymentgateway.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A common request object combining fields
 *  from CheckAccountRequest and ChargeAccountRequest objects.
 */
@ToString
@Getter
@Setter
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class CommonRequestV2 {

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
   * Payment amount format dd, dd.dd, dd.d .
   */
  private String amount;

  /**
   * Internal unique number of the session/transaction/transaction payment system.
   */
  private String session;

  /**
   * Payment date in format «dd.mm.yyyy hh24:mi:ss».
   */
  private String payment_create;

  public String getCheck() {
    return check == null ? "null" : check;
  }

  public CheckAccountRequest createCheckAccountRequest() {
    return new CheckAccountRequest(check, sign, number);
  }

  public ChargeAccountRequest createChargeAccountRequest() {
    return new ChargeAccountRequest(check, sign, number, amount, session, payment_create);

  }

}
