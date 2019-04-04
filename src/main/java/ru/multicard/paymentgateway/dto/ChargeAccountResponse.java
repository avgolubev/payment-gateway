package ru.multicard.paymentgateway.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Object for return result after charged account.
 */
@Getter
@Setter
@XmlRootElement(name = "response")
@NoArgsConstructor
public class ChargeAccountResponse extends ParentResponse {

  /**
   * A sign of the operation: charge account - 0.
   */
  private String check = "0";

  /**
   * Account.
   */
  private String number;

  /**
   * Recommended payment amount.
   */
  private String amount;

  /**
   * Internal unique number of the session/transaction/transaction payment system.
   */
  private String session;

  /**
   * Unique identifier of the Bank session in MSSQL uniqueidentifier format.
   */
  @XmlElement(name = "resSession")
  private String resSession;

  /**
   * Actual date the payment was added to the billing in format  «dd.mm.yyyy hh24:mi:ss».
   */
  @XmlElement(name = "resDate")
  private String resDate;

  /**
   * Payment date in format «dd.mm.yyyy hh24:mi:ss».
   */
  @XmlElement(name = "paymentCreate")
  private String paymentCreate;

}
