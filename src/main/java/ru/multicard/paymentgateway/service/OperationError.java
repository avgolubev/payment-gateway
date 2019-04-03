package ru.multicard.paymentgateway.service;

/**
 * Enum for known operation errors
 */
public enum OperationError {

  NO_ERROR(0, "нет ошибки"),
  XML_REQUEST_ERROR(1, "Ошибка XML запроса"),
  ABSENT_PARAMETER(2, "Ошибка в переданных параметрах запроса. Отсутствует параметр"),
  MD5_ERROR(3, "Ошибка ключа MD5"),
  DB_CONNECT_ERROR(4, "Ошибка подключения к базе данных"),
  DB_REQUEST_ERROR(5, "Ошибка выполнения запроса к базе данных"),
  UKNOWN_IP(6, "Запрет доступа с неизвестного IP адреса (если требуется проверка)"),
  PAYMENT_EXIST(10, "Платёж с указанной сессией уже имеется в базе данных"),
  INVALID_ACCOUNT_FORMAT(20, "Неверный формат номера договора"),
  INVALID_SESSION_FORMAT(21, "Неверный формат сессии"),
  INVALID_AMOUNT_FORMAT(22, "Неверный формат суммы платежа"),
  INVALID_PAYMENT_DATE_FORMAT(23, "Неверный формат даты платежа"),
  NOT_EXIST_ACCOUNT(30, "Не существующий номер договора"),
  CLIENT_REGISTRATION_NOT_COMPLETED(31, "Регистрация клиента не была завершена"),
  CONTRACT_CANCELLATION(32, "Расторжение договора"),
  TOO_SMALL_PAYMENT_AMOUNT(40, "Слишком маленькая сумма платежа"),
  TOO_LARGE_PAYMENT_AMOUNT(41, "Слишком большая сумма платежа"),
  PAYMENT_FOR_ACCOUNT_IS_PROHIBITED(50, "Платёж на указанный номер договора запрещен");

  private final int code;
  private final String text;

  OperationError(int code, String text) {
    this.code = code;
    this.text = text;
  }

  public int getCode() {
    return code;
  }

  public String getText() {
    return text;
  }
}
