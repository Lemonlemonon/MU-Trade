package com.fyp.mumarket.test.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fyp.mumarket.dao.admin.MenuDao;
import com.fyp.mumarket.dao.admin.UserDao;
import com.fyp.mumarket.dao.common.NoticeDao;
import com.fyp.mumarket.dao.common.StudentDao;
import com.fyp.mumarket.entity.admin.Menu;
import com.fyp.mumarket.entity.admin.User;
import com.fyp.mumarket.entity.common.Notice;
import com.fyp.mumarket.entity.common.Student;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class daoTest {
  @Autowired
  private StudentDao studentDao;
  @Autowired
  private UserDao userDao;
  @Autowired
  private MenuDao menuDao;
  @Autowired
  private NoticeDao noticeDao;

  @Before // Create mock objects for Dao
  public void init(){
    studentDao = mock(StudentDao.class);
    userDao = mock(UserDao.class);
    menuDao = mock(MenuDao.class);
    noticeDao = mock(NoticeDao.class);
  }

  @Test
  public void testFindNoticesByReceiverId() {
    Notice notice = new Notice();
    notice.setId(123L);
    notice.setContent("This is a testing");
    notice.setReceiverId(456L);
    ArrayList<Notice> notices = new ArrayList<>();
    notices.add(notice);
    Mockito.when(noticeDao.findNoticesByReceiverId(123L)).thenReturn(notices);

    List<Notice> noticeList = noticeDao.findNoticesByReceiverId(123L);

    assertNotNull(noticeList);
    assertThat(notice.getContent(),equalTo(notices.get(0).getContent()));
    verify(noticeDao,times(1)).findNoticesByReceiverId(123L);
  }

  @Test
  public void testFindBySn() {
    Student student = new Student();
    student.setSn("123");
    student.setNickname("TOM");
    Mockito.when(studentDao.findBySn("123")).thenReturn(student);

    Student studentDaoBySn = studentDao.findBySn("123");

    assertNotNull(studentDaoBySn);
    assertThat(student.getNickname(),equalTo(studentDaoBySn.getNickname()));
    verify(studentDao,times(1)).findBySn("123");
  }

  @Test
  public void testFindById() {
    Student student = new Student();
    student.setId(123L);
    student.setSn("123");
    student.setNickname("TOM");
    Mockito.when(studentDao.find(123L)).thenReturn(student);

    Student studentDaoBySn = studentDao.find(123L);

    assertNotNull(studentDaoBySn);
    assertThat(student.getNickname(),equalTo(studentDaoBySn.getNickname()));
    verify(studentDao,times(1)).find(123L);
  }

  @Test
  public void testUserByUsername() {
    String username = "admin";

    User user1 = new User();
    user1.setUsername(username);

    User user = userDao.findByUsername(username);

    doReturn(user1).when(userDao).findByUsername(username);

    System.out.println(user);

    assertNotNull(user);
    assertThat(username, equalTo(user.getUsername()));
  }

  @Test
  public void testFindMenu() {
    Menu menu = new Menu();
    menu.setId(123L);
    menu.setName("Menu");

    Mockito.when(menuDao.find(123L)).thenReturn(menu);

    Menu menu1 = menuDao.find(123L);

    assertNotNull(menu1);
    assertThat(menu1.getName(),equalTo(menu.getName()));
    verify(menuDao,times(1)).find(123L);


  }




}
