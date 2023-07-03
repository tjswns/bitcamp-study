package bitcamp.myappProject.handler;

import java.util.List;
import bitcamp.myappProject.vo.Board;
import bitcamp.util.BreadcrumbPrompt;

public class BoardDetailListener extends AbstractBoardListener {

  public BoardDetailListener(List<Board> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int boardNo = prompt.inputInt("번호? ");

    Board board = this.findBy(boardNo);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }

    System.out.printf("제목: %s\n", board.getStyle());
    System.out.printf("내용: %s\n", board.getBrand());
    System.out.printf("작성자: %s\n", board.getFit());
    System.out.printf("등록일: %tY-%1$tm-%1$td\n", board.getCreatedDate());

  }
}


