
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
      result.setError(OperationError.ABSENT_PARAMETER);
      return result;
    }

    if(!isValidMd5Hash(request.getSign(), request.getNumber(), salt)) {
      result.setError(OperationError.MD5_ERROR);
      return result;
    }

    result.setError(OperationError.NO_ERROR);

    return result; // execute external system method or DB query
  }

  /**
   * Charge account in external system
   * @param request
   *    Object with data to charge account
   * @return
   *    Result of charging account
   */
  public ChargeAccountResponse chargeAccount(ChargeAccountRequest request) {

    ChargeAccountResponse result = new ChargeAccountResponse();
    result.setNumber(request.getNumber());
    result.setAmount(request.getAmount());
    result.setSession(request.getSession());
    result.setPayment_create(request.getPayment_create());

    if(!request.isAllParametersFilled()) {
      result.setError(OperationError.ABSENT_PARAMETER);
      return result;
    }

    if(!isValidMd5Hash(request.getSign(), request.getNumber(), salt)) {
      result.setError(OperationError.MD5_ERROR);
      return result;
    }

    if(!request.isAmountFormatValid()) {
      result.setError(OperationError.INVALID_AMOUNT_FORMAT);
      return result;
    }

    if(!request.isPaymentCreateFormatValid()) {
      result.setError(OperationError.INVALID_PAYMENT_DATE_FORMAT);
      return result;
    }

    result.setError(OperationError.NO_ERROR);

    return result; // execute external system method or DB query
  }

  /**
   * Validation md5 hash
   *
   * @return
   *    true if hash is valid
   */
  private boolean isValidMd5Hash(String sign, String account, String salt) {

    String md5Hex = DigestUtils.md5Hex(account + salt);
    log.debug(md5Hex);
    return md5Hex.equals(sign);
  }

}
