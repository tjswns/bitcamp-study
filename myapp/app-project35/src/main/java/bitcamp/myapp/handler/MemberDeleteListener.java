package bitcamp.myapp.handler;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.util.BreadcrumbPrompt;

public class MemberDeleteListener implements MemberActionListener {

  MemberDao memberDao;


  public MemberDeleteListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (memberDao.delete(prompt.inputInt("번호? ")) == 0) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }

  }
}
