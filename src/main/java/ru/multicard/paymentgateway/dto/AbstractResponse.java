package ru.multicard.paymentgateway.dto;

import lombok.Getter;
import lombok.Setter;
import ru.multicard.paymentgateway.service.OperationError;

@Getter
@Setter
public abstract class AbstractResponse {

  /**
   * digital error code
   */
  private int retval;

  /**
   * error text
   */
  private String retdesc;

  /**
   * set retval and retdesc by OperationError
   */
  public void setError(OperationError operationError) {
    setRetval(operationError.getCode());
    setRetdesc(operationError.getText());
  }

}
