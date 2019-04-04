package ru.multicard.paymentgateway.dto;

import lombok.Getter;
import lombok.Setter;
import ru.multicard.paymentgateway.service.OperationError;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Object for return result after charged account
 */
@Getter
@Setter
@XmlRootElement(name="response")
public class ChargeAccountResponse extends AbstractResponse {

  /**
   * a sign of the operation: charge account - 0
   */
  private String check = "0";

  /**
   * account
   */
  private String number;

  /**
   * recommended payment amount
   */
  private String amount;

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
  private String res_date;

  /**
   * payment date in format «dd.mm.yyyy hh24:mi:ss»
   */
  private String payment_create;

}
