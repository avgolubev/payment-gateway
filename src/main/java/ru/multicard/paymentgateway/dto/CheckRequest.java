
package ru.multicard.paymentgateway.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author avgolubev
 */
@Getter
@Setter
public class CheckRequest {
  
  private int check;  
  
  private String sign;
  
  private String number;
    
}
