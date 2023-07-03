package bitcamp.myappProject.handler;

import java.util.List;
import bitcamp.myappProject.vo.Board;
import bitcamp.util.BreadcrumbPrompt;

public class BoardAddListener extends AbstractBoardListener {

  public BoardAddListener(List<Board> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Board board = new Board();
    board.setNo(Board.boardNo++);
    board.setStyle(prompt.inputString("좋아하는 스타일? "));
    board.setBrand(prompt.inputString("좋아하는 브랜드? "));
    board.setFit(prompt.inputString("좋아하는 핏? "));
    board.setCreatedDate(System.currentTimeMillis());

    this.list.add(board);
  }
}
