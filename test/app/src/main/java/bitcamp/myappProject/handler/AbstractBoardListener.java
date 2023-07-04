package bitcamp.myappProject.handler;

import java.util.List;
import bitcamp.App13.util.Prompt;
import bitcamp.myappProject.vo.Board;
import bitcamp.util.ActionListener;


public abstract class AbstractBoardListener implements ActionListener {

  protected List<Board> list;

  public AbstractBoardListener(List<Board> list) {
    this.list = list;
  }

  protected static void inputStyle(Board board) {
    loop: while (true) {
      String menuNo =
          Prompt.inputString("좋아하는 스타일:\n" + "  1. 데일리\n" + "  2. 파티\n" + "  3. 데이트\n" + "> ");

      switch (menuNo) {
        case "1":
          board.setStyle("데일리");
          break loop;
        case "2":
          board.setStyle("파티");
          break loop;
        case "3":
          board.setStyle("데이트");
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }


  protected Board findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Board b = this.list.get(i);
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
  }

}
