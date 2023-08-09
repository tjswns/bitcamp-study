package bitcamp.myapp.handler;

import java.io.IOException;
import java.util.List;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/member/list")
public class MemberListListener implements MemberActionListener {

  MemberDao memberDao;

  public MemberListListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    prompt.println("-------------------------------------------------------------");
    prompt.println("번호, 이름, 이메일, 나이, 성별, 상의, 하의, 신발");
    prompt.println("--------------------------------------------------------------");

    List<Member> list = memberDao.findAll();
    for (Member m : list) {
      prompt.printf("%d, %s, %s, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(),
          m.getAge(), m.getGender() == 'M' ? "남성" : "여성", m.getTop(), m.getPants(), m.getShoes());
    }
  }
}
