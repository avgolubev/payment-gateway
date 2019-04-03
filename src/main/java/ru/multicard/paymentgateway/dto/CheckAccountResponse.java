
package ru.multicard.paymentgateway.dto;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * The object of the response when checking the contract number
 */
@Getter
@Setter
@XmlRootElement(name="response")
public class CheckAccountResponse {

	/**
	 * a sign of the operation: checking account - 1
	 */
	private int check = 1;

	/**
	 * digital error code
	 */
	private int retval;

	/**
	 * error text
	 */
	private String retdesc;

	/**
	 * contract number
	 */
	private String number;

	/**
	 * client name
	 */
	private String name;

	/**
	 * address of receiving services by the client
	 */
	private String address;

	/**
	 * recommended payment amount
	 */
	private BigDecimal summa;

}
