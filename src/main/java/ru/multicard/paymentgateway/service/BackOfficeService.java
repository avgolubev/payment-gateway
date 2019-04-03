
package ru.multicard.paymentgateway.service;

import lombok.extern.log4j.Log4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.multicard.paymentgateway.dto.ChargeAccountRequest;
import ru.multicard.paymentgateway.dto.ChargeAccountResponse;
import ru.multicard.paymentgateway.dto.CheckContractNumberRequest;
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

  public CheckAccountResponse checkAccount(CheckContractNumberRequest request) {
    CheckAccountResponse result = new CheckAccountResponse();

    if(!isValidMd5Hash(request.getSign(), request.getNumber(), salt)) {
      result.setRetval(3);
    }

  }


  public ChargeAccountResponse chargeAccount(ChargeAccountRequest chargeAccountRequest) {

  }

  /**
   * Validation md5 hash
   *
   * @return
   *    true if hash is valid
   */
  private boolean isValidMd5Hash(String sign, String account, String salt) {

    String md5Hex = DigestUtils
      .md5Hex(account + salt).toUpperCase();

    return md5Hex.equals(sign);
  }

}
