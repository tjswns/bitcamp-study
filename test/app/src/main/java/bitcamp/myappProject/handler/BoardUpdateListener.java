package bitcamp.myappProject.handler;

import java.util.List;
import bitcamp.myappProject.vo.Board;
import bitcamp.util.BreadcrumbPrompt;

public class BoardUpdateListener extends AbstractBoardListener {

  public BoardUpdateListener(List<Board> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int boardNo = prompt.inputInt("번호? ");

    Board board = this.findBy(boardNo);
    if (board == null) {
      System.out.println("해당 번호의 스타일이 없습니다!");
      return;
    }

    if (!prompt.inputString("fit? ").equals(board.getFit())) {
      System.out.println("일치하는 fit이 없습니다.");
      return;
    }

    inputStyle(board);
    board.setBrand(prompt.inputString("브랜드(%s)? ", board.getBrand()));
  }
}


