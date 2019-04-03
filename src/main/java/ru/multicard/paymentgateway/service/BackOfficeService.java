
package ru.multicard.paymentgateway.service;

import lombok.extern.log4j.Log4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.multicard.paymentgateway.dto.ChargeAccountRequest;
import ru.multicard.paymentgateway.dto.ChargeAccountResponse;
import ru.multicard.paymentgateway.dto.CheckAccountRequest;
import ru.multicard.paymentgateway.dto.CheckAccountResponse;

/**
 *  Service for accessing the Bank's systems
 */
@Log4j
@Service
public class BackOfficeService {

  /**
   * secret signing key from application.properties
   */
  @Value("${salt}")
  private String salt;

  /**
   *
   * @param request
   *    Object with data to check account
   * @return
   *    Result of checking account
   */
  public CheckAccountResponse checkAccount(CheckAccountRequest request) {
    CheckAccountResponse result = new CheckAccountResponse();

    result.setNumber(request.getNumber());

    if(!request.isAllParametersFilled()) {
      result.setRetval(OperationError.ABSENT_PARAMETER.getCode());
      result.setRetdesc(OperationError.ABSENT_PARAMETER.getText());
      return result;
    }

    if(!isValidMd5Hash(request.getSign(), request.getNumber(), salt)) {
      result.setRetval(OperationError.MD5_ERROR.getCode());
      result.setRetdesc(OperationError.MD5_ERROR.getText());
      return result;
    }

    return null; // execute external system method
  }

  /**
   * Charge account in external system
   * @param request
   *    Object with data to charge account
   * @return
   *    Result of charging account
   */
  public ChargeAccountResponse chargeAccount(ChargeAccountRequest request) {

    return null;
  }



  /**
   * Validation md5 hash
   *
   * @return
   *    true if hash is valid
   */
  private boolean isValidMd5Hash(String sign, String account, String salt) {

    String md5Hex = DigestUtils
      .md5Hex(account + salt);

    return md5Hex.equals(sign);
  }

}
