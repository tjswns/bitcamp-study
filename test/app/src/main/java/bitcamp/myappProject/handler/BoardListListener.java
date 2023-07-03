package bitcamp.myappProject.handler;

import java.util.Iterator;
import java.util.List;
import bitcamp.myappProject.vo.Board;
import bitcamp.util.BreadcrumbPrompt;

public class BoardListListener extends AbstractBoardListener {

  public BoardListListener(List<Board> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 스타일, 브랜드, 핏, 등록일");
    System.out.println("---------------------------------------");

    Iterator<Board> iterator = list.iterator();

    while (iterator.hasNext()) {
      Board board = iterator.next();
      System.out.printf("%d, %s, %s, %s, %tY-%5$tm-%5$td\n", board.getNo(), board.getStyle(),
          board.getBrand(), board.getFit(), board.getCreatedDate());
    }
  }

}


