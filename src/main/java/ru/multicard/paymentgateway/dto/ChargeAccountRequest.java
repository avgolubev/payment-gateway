package ru.multicard.paymentgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.regex.Pattern;

/**
 * Object with data to charge account.
 */
@ToString
@Getter
@Setter
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
public class ChargeAccountRequest {
  /**
   * A sign of the operation: charge account - 0.
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
  @XmlElement(name = "payment_create")
  private String paymentCreate;

  /**
   * All parameters filled in HTTP request.
   * @return
   *    true if all parameters filled
   */
  public final boolean isAllParametersFilled() {

    return check != null && !check.equals("") && sign != null && !sign.equals("")
      && number != null && !number.equals("")
      && amount != null && !amount.equals("")
      && session != null && !session.equals("")
      && paymentCreate != null && !paymentCreate.equals("");
  }

  /**
   * Checking amount format.
   * @return
   *   true if valid or false
   */
  public final boolean isAmountFormatValid() {
    return Pattern.matches("^\\d{1,6}(\\.\\d{1,2})?$", amount);
  }

  /**
   * Checking paymentCreate format.
   * @return
   *    true if valid or false
   */
  public final boolean isPaymentCreateFormatValid() {
    return Pattern.matches("^\\d{2}\\.\\d{2}.\\d{4}\\s\\d{2}:\\d{2}:\\d{2}$", paymentCreate);
  }

}
