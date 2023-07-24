package bitcamp.myapp.handler;

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



}
