package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;

public class StylingUpdateListener implements StylingActionListener {

  StylingDao stylingDao;

  public StylingUpdateListener(StylingDao stylingDao) {
    this.stylingDao = stylingDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int stylingNo = prompt.inputInt("번호? ");

    Styling styling = stylingDao.findBy(stylingNo);
    if (styling == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    StylingActionListener.inputStyle(styling, prompt);
    StylingActionListener.inputBrand(styling, prompt);
    StylingActionListener.inputFit(styling, prompt);
    styling.setWriter((Member) prompt.getAttribute("loginUser"));

    if (stylingDao.update(styling) == 0) {
      System.out.println("게시글 변경 권한이 없습니다.");
    } else {
      System.out.println("변경했습니다!");
    }
  }
}
