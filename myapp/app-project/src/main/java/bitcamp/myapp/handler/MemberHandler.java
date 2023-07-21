package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class MemberHandler implements Handler {

  private BreadcrumbPrompt prompt;
  private String title;

  public MemberHandler(BreadcrumbPrompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  public void execute() {
    prompt.appendBreadcrumb(this.title, getMenu());

    prompt.printMenu();
    while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0":
          prompt.removeBreadcrumb();
          return;
        case "1":
          this.inputMember();
          break;
        case "2":
          this.printMembers();
          break;
        case "3":
          this.viewMember();
          break;
        case "4":
          this.updateMember();
          break;
        case "5":
          this.deleteMember();
          break;
      }
    }
  }

  private static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 회원등록\n");
    menu.append("2. 회원목록\n");
    menu.append("3. 회원조회\n");
    menu.append("4. 회원변경\n");
    menu.append("5. 회원삭제\n");
    menu.append("0. 메인\n");
    return menu.toString();
  }



  public void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 상의, 하의, 신발, 성별");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      System.out.printf("%d, %s, %s, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getAge(),
          m.getTop(), m.getPants(), m.getShoes(), toGenderString(m.getGender()));
    }
  }

  private void viewMember() {
    int memberNo = this.prompt.inputInt("번호? ");

    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("나이: %s\n", m.getAge());
    System.out.printf("성별: %s\n", toGenderString(m.getGender()));
    System.out.printf("상의: %s\n", m.getTop());
    System.out.printf("바지: %s\n", m.getPants());
    System.out.printf("신발: %s\n", m.getShoes());

  }


  public void updateMember() {
    int memberNo = this.prompt.inputInt("번호? ");
    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    m.setName(this.prompt.inputString("이름(%s)?", m.getName()));
    m.setAge(this.prompt.inputString("나이(%s)? ", m.getAge()));
    m.setGender(inputGender(m.getGender()));
    inputTop(m);
    inputPants(m);
    inputShoes(m);
  }



  public void deleteMember() {
    if (!this.list.remove(new Member(this.prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }

  }


}
