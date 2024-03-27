package com.fyp.mumarket.test.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.fyp.mutrade.dao.admin.MenuDao;
import com.fyp.mutrade.dao.admin.UserDao;
import com.fyp.mutrade.entity.admin.Menu;
import com.fyp.mutrade.entity.admin.OperaterLog;
import com.fyp.mutrade.entity.admin.User;
import com.fyp.mutrade.service.admin.MenuService;
import com.fyp.mutrade.service.admin.OperaterLogService;
import com.fyp.mutrade.service.admin.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ServiceTest {
  @Autowired
  private UserDao userDao;
  @Autowired
  private MenuDao menuDao;
  @Autowired
  private OperaterLogService operaterLogService;

  @Before //Create mock objects for Dao
  public void init() {
    userDao = mock(UserDao.class);
    menuDao = mock(MenuDao.class);
  }

  /**
   * User information querying testing
   */
  @Test
  public void testFindByUsername() {

    // Mock test data
    User user = new User();
    user.setUsername("tom");
    user.setEmail("8888@gmail.com");
    user.setMobile("12456");
    user.setSex(0);
    doReturn(user).when(userDao).findByUsername("tom");

    // Using ReflectionTestUtils to set private attributes for the target object with mock
    UserService userService = new UserService();
    ReflectionTestUtils.setField(userService, "userDao", userDao);

    //Verify the service method using mock
    User u = userService.findByUsername("tom");
    assertNotNull(u);
    assertThat(u.getUsername(), equalTo(user.getUsername()));

    //Verify interaction with mock
    verify(userDao, times(1)).findByUsername("tom");
  }

  /**
   * Test for saving user information
   */
  @Test
  public void testSaveUser() {

    //Mock test data
    User user = new User();
    user.setUsername("jerry");
    user.setEmail("8888@gmail.com");
    user.setMobile("654321");
    user.setSex(0);
    doReturn(user).when(userDao).save(user);

    // Using ReflectionTestUtils to set private attributes for the target object with mock
    UserService userService = new UserService();
    ReflectionTestUtils.setField(userService, "userDao", userDao);

    // Verify the service method using mock
    User u = userService.save(user);
    assertNotNull(u);
    assertThat(u.getUsername(), equalTo(user.getUsername()));

    // Verify interaction with mock
    verify(userDao, times(1)).save(user);
  }

  /**
   * Test for saving menu information
   */
  @Test
  public void testSaveMenu() {

    // Mock test data
    Menu menu = new Menu();
    menu.setName("Menu");
    menu.setShow(true);
    doReturn(menu).when(menuDao).save(menu);

    // Using ReflectionTestUtils to set private attributes for the target object with mock
    MenuService menuService = new MenuService();
    ReflectionTestUtils.setField(menuService, "menuDao", menuDao);

    // Verify interaction with mock
    Menu menu1 = menuService.save(menu);
    assertNotNull(menu1);
    assertThat(menu1.getName(), equalTo(menu.getName()));

    // Verify interaction with mock
    verify(menuDao, times(1)).save(menu);
  }

  /**
   * Test for saving operation log
   */
  @Test
  public void testSaveOperatorLog() {
    OperaterLog operaterLog = new OperaterLog();
    operaterLog.setOperator("operator");
    operaterLog.setContent("this is content");

    OperaterLog log = operaterLogService.save(operaterLog);

    System.out.println("Saved log: " + log);

  }


  /**
   * Test for finding operation log
   */
  @Test
  public void testFindLastestLog() {
    Integer size = 5;
    List<OperaterLog> lastestLog = operaterLogService.findLastestLog(size);

    for (OperaterLog operaterLog : lastestLog) {
      System.out.println("Log：" + operaterLog);
    }
  }


}
