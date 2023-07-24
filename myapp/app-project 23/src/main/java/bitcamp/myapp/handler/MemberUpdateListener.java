package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class MemberUpdateListener extends AbstractMemberListener {

  public MemberUpdateListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");
    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    m.setName(prompt.inputString("이름(%s)?", m.getName()));
    m.setAge(prompt.inputString("나이(%s)? ", m.getAge()));
    m.setGender(inputGender(m.getGender(), prompt));
    inputTop(m, prompt);
    inputPants(m, prompt);
    inputShoes(m, prompt);
  }
}
