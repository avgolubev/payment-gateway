
package ru.multicard.paymentgateway.service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.multicard.paymentgateway.dto.ChargeAccountRequest;
import ru.multicard.paymentgateway.dto.ChargeAccountResponse;
import ru.multicard.paymentgateway.dto.CheckAccountRequest;
import ru.multicard.paymentgateway.dto.CheckAccountResponse;

import static ru.multicard.paymentgateway.service.OperationError.*;

/**
 *  Service for accessing the Bank's systems.
 */
@Log4j
@Service
@NoArgsConstructor
public class BackOfficeService {

  /**
   * Secret signing key from application.properties.
   */
  @Value("${salt}")
  private String salt;

  /**
   * Check account.
   * @param request
   *    object with data to check account
   * @return
   *    result of checking account
   */
  public final CheckAccountResponse checkAccount(final CheckAccountRequest request) {

    final CheckAccountResponse result = new CheckAccountResponse();
    result.setNumber(request.getNumber());

    if (!request.isAllParametersFilled()) {
      result.setError(ABSENT_PARAMETER);
      return result;
    }

    if (!isValidMd5Hash(request.getSign(), request.getNumber())) {
      result.setError(MD5_ERROR);
      return result;
    }

    result.setError(NO_ERROR);

    return result; // execute external system method or DB query
  }

  /**
   * Charge account in external system.
   * @param request
   *    object with data to charge account
   * @return
   *    result of charging account
   */
  public final ChargeAccountResponse chargeAccount(final ChargeAccountRequest request) {

    final ChargeAccountResponse result = new ChargeAccountResponse();
    result.setNumber(request.getNumber());
    result.setAmount(request.getAmount());
    result.setSession(request.getSession());
    result.setPayment_create(request.getPaymentCreate());

    if (!request.isAllParametersFilled()) {
      result.setError(ABSENT_PARAMETER);
      return result;
    }

    if (!isValidMd5Hash(request.getSign(), request.getNumber())) {
      result.setError(MD5_ERROR);
      return result;
    }

    if (!request.isAmountFormatValid()) {
      result.setError(INVALID_AMOUNT_FORMAT);
      return result;
    }

    if (!request.isPaymentCreateFormatValid()) {
      result.setError(INVALID_PAYMENT_DATE_FORMAT);
      return result;
    }

    result.setError(NO_ERROR);

    return result; // execute external system method or DB query
  }

  /**
   * Validation md5 hash.
   * @param sign
   *    md5 hash from http request
   * @param account
   *    account from http request
   * @return
   *    true if hash is valid
   */
  private boolean isValidMd5Hash(final String sign, final String account) {

    final String md5Hex = DigestUtils.md5Hex(account + salt);
    log.debug(md5Hex);
    return md5Hex.equals(sign);
  }

}
