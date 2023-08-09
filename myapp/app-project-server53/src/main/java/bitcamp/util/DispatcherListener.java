package bitcamp.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.MySQLAccDao;
import bitcamp.myapp.dao.MySQLMemberDao;
import bitcamp.myapp.dao.MySQLStylingDao;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.handler.AccAddListener;
import bitcamp.myapp.handler.AccDeleteListener;
import bitcamp.myapp.handler.AccDetailListener;
import bitcamp.myapp.handler.AccListListener;
import bitcamp.myapp.handler.AccUpdateListener;
import bitcamp.myapp.handler.LoginListener;
import bitcamp.myapp.handler.MemberAddListener;
import bitcamp.myapp.handler.MemberDeleteListener;
import bitcamp.myapp.handler.MemberDetailListener;
import bitcamp.myapp.handler.MemberListListener;
import bitcamp.myapp.handler.MemberUpdateListener;
import bitcamp.myapp.handler.StylingAddListener;
import bitcamp.myapp.handler.StylingDeleteListener;
import bitcamp.myapp.handler.StylingDetailListener;
import bitcamp.myapp.handler.StylingListListener;
import bitcamp.myapp.handler.StylingUpdateListener;

public class DispatcherListener implements ActionListener {

  // 객체 보관소
  Map<String, Object> beanContainer = new HashMap<>();

  public DispatcherListener() throws Exception {

    // Mybatis 준비
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsStream("bitcamp/myapp/config/mybatis-config.xml")));
    beanContainer.put("sqlSessionFactory", sqlSessionFactory);

    // DAO 준비
    MemberDao memberDao = new MySQLMemberDao(sqlSessionFactory);
    AccDao accDao = new MySQLAccDao(sqlSessionFactory);
    StylingDao stylingDao = new MySQLStylingDao(sqlSessionFactory);
    beanContainer.put("memberDao", memberDao);
    beanContainer.put("accDao", accDao);
    beanContainer.put("stylingDao", stylingDao);
    // Listener 준비
    beanContainer.put("login", new LoginListener(memberDao));

    beanContainer.put("member/add", new MemberAddListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/list", new MemberListListener(memberDao));
    beanContainer.put("member/detail", new MemberDetailListener(memberDao));
    beanContainer.put("member/update", new MemberUpdateListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/delete", new MemberDeleteListener(memberDao, sqlSessionFactory));

    beanContainer.put("styling/add", new StylingAddListener(1, stylingDao, sqlSessionFactory));
    beanContainer.put("styling/list", new StylingListListener(1, stylingDao));
    beanContainer.put("styling/detail",
        new StylingDetailListener(1, stylingDao, sqlSessionFactory));
    beanContainer.put("styling/update",
        new StylingUpdateListener(1, stylingDao, sqlSessionFactory));
    beanContainer.put("styling/delete",
        new StylingDeleteListener(1, stylingDao, sqlSessionFactory));

    beanContainer.put("acc/add", new AccAddListener(2, accDao, sqlSessionFactory));
    beanContainer.put("acc/list", new AccListListener(2, accDao));
    beanContainer.put("acc/detail", new AccDetailListener(2, accDao, sqlSessionFactory));
    beanContainer.put("acc/update", new AccUpdateListener(2, accDao, sqlSessionFactory));
    beanContainer.put("acc/delete", new AccDeleteListener(2, accDao, sqlSessionFactory));
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    ActionListener listener = (ActionListener) beanContainer.get(prompt.getAttribute("menuPath"));
    if (listener == null) {
      throw new RuntimeException("해당 요청을 처리할 수 없습니다.");
    }
    listener.service(prompt);
  }

  public Object getBean(String name) {
    return beanContainer.get(name);
  }
}
