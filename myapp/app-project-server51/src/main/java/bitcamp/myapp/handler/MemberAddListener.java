package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class MemberAddListener implements MemberActionListener {

  MemberDao memberDao;
  DataSource ds;

  public MemberAddListener(MemberDao memberDao, DataSource ds) {
    this.memberDao = memberDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Member m = new Member();
    m.setName(prompt.inputString("이름? "));
    m.setEmail(prompt.inputString("이메일? "));
    m.setPassword(prompt.inputString("비밀번호? "));
    m.setAge(prompt.inputString("나이? "));
    m.setGender(MemberActionListener.inputGender((char) 0, prompt));
    MemberActionListener.inputTop(m, prompt);
    MemberActionListener.inputPants(m, prompt);
    MemberActionListener.inputShoes(m, prompt);

    try {
      memberDao.insert(m);
      ds.getConnection().commit();

    } catch (Exception e) {
      try {
        ds.getConnection().rollback();
      } catch (Exception e2) {
      }
      throw new RuntimeException(e);
    }
  }
}
