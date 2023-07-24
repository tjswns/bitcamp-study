package bitcamp.myapp.handler;

import java.util.List;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class MemberListListener extends AbstractMemberListener {

  public MemberListListener(List<Member> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("-------------------------------------------------------------");
    System.out.println("번호, 이름, 이메일, 비밀번호, 나이, 상의, 하의, 신발, 성별");
    System.out.println("--------------------------------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Member m = this.list.get(i);
      System.out.printf("%d, %s, %s, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getAge(),
          m.getTop(), m.getPants(), m.getShoes(), toGenderString(m.getGender()));
    }
  }
}
