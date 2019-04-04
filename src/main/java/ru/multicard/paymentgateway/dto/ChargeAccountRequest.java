package ru.multicard.paymentgateway.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Object with data to charge account
 */
@ToString
@Getter
@Setter
@XmlRootElement(name="request")
@AllArgsConstructor
@NoArgsConstructor
public class ChargeAccountRequest {
  /**
   * a sign of the operation: account replenishment - 0
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
   * payment amount format dd, dd.dd, dd.d
   */
  private String amount;

  /**
   * internal unique number of the session/transaction/transaction payment system
   */
  private String session;

  /**
   * payment date in format «dd.mm.yyyy hh24:mi:ss»
   */
  private String payment_create;

  /**
   * All parameters filled in HTTP request
   */
  public boolean isAllParametersFilled() {

    return check != null && !check.equals("") && sign != null && !sign.equals("")
      && number != null && !number.equals("")
      && amount != null && !amount.equals("")
      && session != null && !session.equals("")
      && payment_create != null && !payment_create.equals("");
  }

  /**
   * Checking amount format
   * @return
   *   true if valid or false
   */
  public boolean isAmountFormatValid() {
    return Pattern.matches("^\\d{1,6}(\\.\\d{1,2})?$", amount);
  }

  /**
   * Checking payment_create format
   * @return
   *    true if valid or false
   */
  public boolean isPaymentCreateFormatValid() {
    return Pattern.matches("^\\d{2}\\.\\d{2}.\\d{4}\\s\\d{2}:\\d{2}:\\d{2}$", payment_create);
  }

}
