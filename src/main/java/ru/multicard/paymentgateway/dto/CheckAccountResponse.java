
package ru.multicard.paymentgateway.dto;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Object for response when checking account.
 */
@Getter
@Setter
@XmlRootElement(name = "response")
@NoArgsConstructor
public class CheckAccountResponse extends ParentResponse {

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

}
