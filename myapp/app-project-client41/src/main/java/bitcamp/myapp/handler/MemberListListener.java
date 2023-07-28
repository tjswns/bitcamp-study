package bitcamp.myapp.handler;

import java.util.List;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class MemberListListener implements MemberActionListener {

  MemberDao memberDao;

  public MemberListListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("-------------------------------------------------------------");
    System.out.println("번호, 이름, 이메일, 비밀번호, 나이, 상의, 하의, 신발, 성별");
    System.out.println("--------------------------------------------------------------");

    List<Member> list = memberDao.list();
    for (Member m : list) {
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(),
          m.getPassword(), m.getAge(), m.getTop(), m.getPants(), m.getShoes(),
          m.getGender() == 'M' ? "남성" : "여성");
    }
  }
}
