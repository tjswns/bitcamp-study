package bitcamp.myappProject.handler;

import java.util.Iterator;
import java.util.List;
import bitcamp.myappProject.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class MemberListListener extends AbstractMemberListener {

  public MemberListListener(List<Member> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 성별, 상의, 하의, 신발");
    System.out.println("---------------------------------------");

    // 목록에서 데이터를 대신 꺼내주는 객체를 얻는다.
    Iterator<Member> iterator = list.iterator();
    while (iterator.hasNext()) {
      Member m = iterator.next();
      System.out.printf("%d, %s, %s, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getAge(),
          toGenderString(m.getGender()), m.getTop(), m.getPants(), m.getShoes());
    }
  }

}
