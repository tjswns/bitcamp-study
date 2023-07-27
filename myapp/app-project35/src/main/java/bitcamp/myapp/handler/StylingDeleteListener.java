package bitcamp.myapp.handler;

import bitcamp.myapp.dao.StylingDao;
import bitcamp.util.BreadcrumbPrompt;

public class StylingDeleteListener implements StylingActionListener {

  StylingDao stylingDao;

  public StylingDeleteListener(StylingDao stylingDao) {
    this.stylingDao = stylingDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (stylingDao.delete(prompt.inputInt("번호? ")) == 0) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }
}
