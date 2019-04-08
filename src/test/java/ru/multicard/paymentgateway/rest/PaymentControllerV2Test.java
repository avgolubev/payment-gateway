package ru.multicard.paymentgateway.rest;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ru.multicard.paymentgateway.service.BackOfficeService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j
@RunWith(JUnitPlatform.class)
@SpringBootTest
@NoArgsConstructor
@DisplayName("Test PaymentControllerV2.")
class PaymentControllerV2Test {

  private MockMvc mockMvc;

  @Autowired
  BackOfficeService backOfficeService;

  @BeforeEach
  void setUp() {

    mockMvc = MockMvcBuilders.standaloneSetup(new PaymentControllerV2(backOfficeService)).build();
  }

  @Test
  @DisplayName("Check choosing right route by value of check parameter and return xml in «application/xml;charset=UTF-8» "
             + "content-type.")
  void checkGetOperationAccountRightRouteAndContentType() throws Exception {

    MockHttpServletRequestBuilder requestBuilder =
      MockMvcRequestBuilders.get("/" + createCheckQuery("1","123","xxx"))
        .contentType(MediaType.APPLICATION_XML).characterEncoding("UTF-8");

    MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

    MockHttpServletResponse mockResponse = result.getResponse();
    assertEquals(mockResponse.getContentType(), "application/xml;charset=UTF-8");
    String responseAsString=mockResponse.getContentAsString();
    log.info(responseAsString);
    assertTrue(responseAsString.contains("<response><check>1</check><retval>3</retval>"
            + "<retdesc>Ошибка ключа MD5</retdesc><number>123</number></response>"));

    requestBuilder = MockMvcRequestBuilders.get("/" +
            createChargeQuery("0","123","xxx", "12", "xxx", "01.01.2019 12:34:56"))
                    .contentType(MediaType.APPLICATION_XML).characterEncoding("UTF-8");

    result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

    mockResponse = result.getResponse();
    assertEquals(mockResponse.getContentType(), "application/xml;charset=UTF-8");
    responseAsString=mockResponse.getContentAsString();
    log.info(responseAsString);
    assertTrue(responseAsString.contains("<response><check>0</check><retval>3</retval>"
            + "<retdesc>Ошибка ключа MD5</retdesc><number>123</number><amount>12</amount>"
            + "<session>xxx</session><payment_create>01.01.2019 12:34:56</payment_create></response>"));

  }

  @Test
  void postOperationAccount() {
  }


  private static String createCheckQuery (String check , String number, String sign) {
    return  "<request>" +
            "<check>" + check + "</check>" +
            "<number>" + number + "</number>" +
            "<sign>" + sign + "</sign>" +
            "</request>";
  }

  private static String createCheckXml (String check , String number, String sign) {
    return "?check=" + check +
      "&number=" + number +
      "&sign=" + sign;
  }

  private static String createChargeQuery (String check , String number, String sign, String amount, String session,
                                           String payment_create) {
    return "?check=" + check +
            "&number=" + number +
            "&sign=" + sign +
            "&amount=" + amount +
            "&session="+ session +
            "&payment_create=" + payment_create;
  }

  private static String createChargeXml (String check , String number, String sign, String amount, String session,
                                           String payment_create) {
    return  "<request>" +
            "<check>" + check + "</check>" +
            "<number>" + number + "</number>" +
            "<sign>" + sign + "</sign>" +
            "<amount>" + amount + "<amount>" +
            "<session>"+ session + "</session>" +
            "<payment_create>" + payment_create + "</payment_create>" +
            "</request>";
  }

}