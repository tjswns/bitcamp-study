package bitcamp.myapp.handler;

import bitcamp.util.BreadcrumbPrompt;

public class StylingHandler implements Handler {

  private BreadcrumbPrompt prompt;
  private String title;

  public StylingHandler(BreadcrumbPrompt prompt, String title) {
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
          this.inputStyling();
          break;
        case "2":
          this.printStyling();
          break;
        case "3":
          this.viewStyling();
          break;
        case "4":
          this.updateStyling();
          break;
        case "5":
          this.deleteStyling();
          break;
      }
    }
  }

  private static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 스타일등록\n");
    menu.append("2. 스타일목록\n");
    menu.append("3. 스타일조회\n");
    menu.append("4. 스타일변경\n");
    menu.append("5. 스타일삭제\n");
    menu.append("0. 메인\n");
    return menu.toString();
  }



}
