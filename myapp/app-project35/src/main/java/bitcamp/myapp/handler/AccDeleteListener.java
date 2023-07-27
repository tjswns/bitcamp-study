package bitcamp.myapp.handler;

import bitcamp.myapp.dao.AccDao;
import bitcamp.util.BreadcrumbPrompt;

public class AccDeleteListener implements AccActionListener {

  AccDao accDao;

  public AccDeleteListener(AccDao accDao) {
    this.accDao = accDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (accDao.delete(prompt.inputInt("번호? ")) == 0) {
      System.out.println("해당 번호의 게시글이 없습니다!");
    }
  }
}
