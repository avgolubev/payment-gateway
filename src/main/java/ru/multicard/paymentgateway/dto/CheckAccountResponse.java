
package ru.multicard.paymentgateway.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;
import ru.multicard.paymentgateway.service.OperationError;

/**
 * Object for response when checking account.
 */
@ToString
@Getter
@Setter
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class CheckAccountResponse {
   /**
	 * A sign of the operation: checking account - 1.
	 */
	private String check = "1";

	/**
	 * Digital error code.
	 */
	private int retval;

	/**
	 * Error text.
	 */
	private String retdesc;

	/**
	 * Contract number.
	 */
	private String number;

	/**
	 * Client name.
	 */
	private String name;

	/**
	 * Address of receiving services by the client.
	 */
	private String address;

	/**
	 * Recommended payment amount.
	 */
	private String summa;

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
