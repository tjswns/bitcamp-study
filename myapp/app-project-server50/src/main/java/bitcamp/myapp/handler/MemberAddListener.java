package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class MemberAddListener implements MemberActionListener {

  MemberDao memberDao;

  public MemberAddListener(MemberDao memberDao) {
    this.memberDao = memberDao;
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

    memberDao.insert(m);
  }
}
