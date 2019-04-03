package ru.multicard.paymentgateway.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@XmlRootElement(name="response")
public class ChargeAccountResponse {

  /**
   * a sign of the operation: account replenishment - 0
   */
  private int check;

  /**
   * digital error code
   */
  private int retval;

  /**
   * error text
   */
  private String retdesc;

  /**
   * account
   */
  private String number;

  /**
   * recommended payment amount
   */
  private BigDecimal amount;

  /**
   * internal unique number of the session/transaction/transaction payment system
   */
  private String session;

  /**
   * unique identifier of the Bank session in MSSQL uniqueidentifier format
   */
  private String res_session;

  /**
   * actual date the payment was added to the billing in format  «dd.mm.yyyy hh24:mi:ss»
   */
  private LocalDate res_date;

  /**
   * payment date in format «dd.mm.yyyy hh24:mi:ss»
   */
  private String payment_create;

}
