package bitcamp.myapp.handler;

import bitcamp.myapp.ClientApp;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;

public class AccDeleteListener implements AccActionListener {

  AccDao accDao;

  public AccDeleteListener(AccDao accDao) {
    this.accDao = accDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {

    Acc acc = new Acc();
    acc.setNo(prompt.inputInt("번호?"));
    acc.setWriter(ClientApp.loginUser);

    if (accDao.delete(acc) == 0) {
      System.out.println("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
    } else {
      System.out.println("삭제했습니다.");
    }
  }
}
