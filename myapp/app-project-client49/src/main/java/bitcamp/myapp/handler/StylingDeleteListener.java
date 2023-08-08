package bitcamp.myapp.handler;

import bitcamp.myapp.ClientApp;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;

public class StylingDeleteListener implements StylingActionListener {

  StylingDao stylingDao;

  public StylingDeleteListener(StylingDao stylingDao) {
    this.stylingDao = stylingDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {

    Styling sty = new Styling();
    sty.setNo(prompt.inputInt("번호? "));
    sty.setWriter(ClientApp.loginUser);

    if (stylingDao.delete(sty) == 0) {
      System.out.println("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
    } else {
      System.out.println("삭제했습니다.");
    }
  }
}
