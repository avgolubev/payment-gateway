
package ru.multicard.paymentgateway.dto;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author avgolubev
 */
@XmlRootElement(name="request")
@Getter
@Setter
public class CheckResponse {

  private int check;

}
