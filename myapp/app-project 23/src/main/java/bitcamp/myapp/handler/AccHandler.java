package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class AccHandler implements Handler {


  private List list;
  private BreadcrumbPrompt prompt;
  private String title;

  public AccHandler(BreadcrumbPrompt prompt, String title, List list) {
    this.prompt = prompt;
    this.title = title;
    this.list = list;
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
          this.inputAcc();
          break;
        case "2":
          this.printAcc();
          break;
        case "3":
          this.viewAcc();
          break;
        case "4":
          this.updateAcc();
          break;
        case "5":
          this.deleteAcc();
          break;
      }
    }
  }

  private static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. acc등록\n");
    menu.append("2. acc목록\n");
    menu.append("3. acc조회\n");
    menu.append("4. acc변경\n");
    menu.append("5. acc삭제\n");
    menu.append("0. 메인\n");
    return menu.toString();
  }



  private Acc findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Acc acc = (Acc) this.list.get(i);
      if (acc.getNo() == no) {
        return acc;
      }
    }
    return null;
  }
}
