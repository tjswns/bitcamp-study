package bitcamp.myappProject.handler;

import java.util.List;
import bitcamp.myappProject.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class MemberDetailListener extends AbstractMemberListener {

  public MemberDetailListener(List<Member> list) {
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

    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("나이: %s\n", m.getAge());
    System.out.printf("성별: %s\n", toGenderString(m.getGender()));
    System.out.printf("상의: %s\n", m.getTop());
    System.out.printf("하의: %s\n", m.getPants());
    System.out.printf("신발: %s\n", m.getShoes());
  }
}
