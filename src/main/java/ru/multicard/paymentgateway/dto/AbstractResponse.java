package ru.multicard.paymentgateway.dto;

import lombok.Getter;
import lombok.Setter;
import ru.multicard.paymentgateway.service.OperationError;

/**
 * Parent for all response objects.
 */
@Getter
@Setter
public abstract class AbstractResponse {

  /**
   * Digital error code.
   */
  private int retval;

  /**
   * Error text.
   */
  private String retdesc;

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
