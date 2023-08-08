package bitcamp.myapp.handler;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class MemberDetailListener implements MemberActionListener {

  MemberDao memberDao;


  public MemberDetailListener(MemberDao memberDao) {
    this.memberDao = memberDao;

  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("이메일: %s\n", m.getEmail());
    System.out.printf("비밀번호: %s\n", m.getPassword());
    System.out.printf("나이: %s\n", m.getAge());
    System.out.printf("성별: %s\n", m.getGender() == 'M' ? "남성" : "여성");
    System.out.printf("상의: %s\n", m.getTop());
    System.out.printf("바지: %s\n", m.getPants());
    System.out.printf("신발: %s\n", m.getShoes());
    System.out.printf("가입일: %s\n", m.getCreatedDate());
  }
}
