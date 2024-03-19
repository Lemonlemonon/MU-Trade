package com.fyp.mumarket.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fyp.mumarket.bean.CodeMsg;
import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.controller.admin.StudentController;
import com.fyp.mumarket.controller.user.UserNoticeController;
import com.fyp.mumarket.entity.common.MsgVo;
import java.nio.charset.Charset;
import javax.annotation.Resource;
import org.approvaltests.Approvals;
import org.approvaltests.JsonJacksonApprovals;
import org.approvaltests.core.Options;
import org.approvaltests.core.Scrubber;
import org.approvaltests.scrubbers.RegExScrubber;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

  @Autowired
  private UserNoticeController userNoticeController;
  @Autowired
  private StudentController studentController;
  @Autowired
  private MockMvc mvc;

  private Scrubber timestampScrubber =
      new RegExScrubber("\"timestamp\" : \\d{13}", "\"timestamp\" : 1111111111111");

  @Test
  void testAdsCategoryList() throws Exception {
    String username = "";
//    String password = "";
    String resutl = mvc.perform(get("/admin/financePay/list").header("X_ACCESS_TOKEN", "token"))
        .andDo(print())
        .andReturn()
        .getResponse()
        .getContentAsString();

    Options options = Approvals.NAMES
        .withParameters(username)
        .withScrubber(timestampScrubber);

    JsonJacksonApprovals.verifyAsJson(new ObjectMapper().readTree(resutl), options);

  }

  /**
   * Testing for user message sending 
   */
  @Test
  public void testUserSendNotice() {
    // Mock message sender id
    Long receiverId = 123L;
    // Mock HTTP request to call interface
    Result<?> result = userNoticeController.sendMsg(new MsgVo(receiverId, "sendId", "content"));
    // Print the result of the method call to the console
    System.out.println("Result of the method sendMsg(): " + result.getData());
    // Mock expected result on success
    Result<String> success = Result.success("Message sent!");
    // Assert that the return value of sendMsg() method matches the expected data
    Assert.assertEquals(success.toString(), result.getData().toString());
  }

  @Test
  public void testQueryNotice() {
    // Mock message sender id
    Long userId = 123L;
    // Mock HTTP request to call interface
    Result<?> result = userNoticeController.queryNotice(userId);
    // Print the result of the method call to the console
    System.out.println("Result of queryNotice() method: " + result.getData());
    // Mock expected result on success
    Assert.assertNotNull(result.getData());
  }

  @Test
  public void testRead() {
    // Mock message sender id
    Long noticeId = 123L;
    // Mock HTTP request to call interface
    Result<?> result = userNoticeController.read(noticeId);
    // Print the result of the method call to the console
    System.out.println("Result of read() method:" + result.getData());
    // Mock expected success result
    Result<String> success = Result.success("Message read!");
    // Assert that the return value of read() method matches the expected data
    Assert.assertEquals(success.toString(), result.getData().toString());
  }

  @Test
  public void testUpDown() {
    // Mock parameters
    Long id = 123L;
    Integer status = 123;
    Result<Boolean> booleanResult = studentController.upDown(id, status);
    // Print the result of the method call to the console
    System.out.println("Result of upDown() method: " + booleanResult);
    // Mock expected error result
    Result<Object> error = Result.error(CodeMsg.ADMIN_ADS_NO_EXIST);
    // Assert that the return value of upDown() method matches the expected data
    Assert.assertEquals(booleanResult.toString(), error.toString());
  }


}
