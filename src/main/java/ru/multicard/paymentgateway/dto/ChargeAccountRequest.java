package ru.multicard.paymentgateway.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Object with data to charge account
 */
@ToString
@Getter
@Setter
@XmlRootElement(name="request")
public class ChargeAccountRequest {
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

  /**
   * payment amount
   */
  private BigDecimal amount;

  /**
   * internal unique number of the session/transaction/transaction payment system
   */
  private String session;

  /**
   * payment date in format «dd.mm.yyyy hh24:mi:ss»
   */
  private LocalDate payment_create;

}
