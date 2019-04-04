package ru.multicard.paymentgateway.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.multicard.paymentgateway.service.OperationError;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Object for return result after charged account.
 */
@ToString
@Getter
@Setter
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ChargeAccountResponse {

  /**
   * A sign of the operation: charge account - 0.
   */
  private String check = "0";

  /**
   * Digital error code.
   */
  private int retval;

  /**
   * Error text.
   */
  private String retdesc;

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
  @XmlElement(name = "res_session")
  private String resSession;

  /**
   * Actual date the payment was added to the billing in format  «dd.mm.yyyy hh24:mi:ss».
   */
  @XmlElement(name = "res_date")
  private String resDate;

  /**
   * Payment date in format «dd.mm.yyyy hh24:mi:ss».
   */
  @XmlElement(name = "payment_create")
  private String paymentCreate;

  /**
   * Set retval and retdesc by OperationError.
   * @param operationError
   *    operation error
   */
  public final void setError(final OperationError operationError) {
    setRetval(operationError.getCode());
    setRetdesc(operationError.getText());
  }

}
