package bitcamp.myapp.handler;

import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;

public class StylingUpdateListener implements StylingActionListener {

  StylingDao stylingDao;

  public StylingUpdateListener(StylingDao stylingDao) {
    this.stylingDao = stylingDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int stylingNo = prompt.inputInt("번호? ");

    Styling styling = stylingDao.findBy(stylingNo);
    if (styling == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    if (!prompt.inputString("암호? ").equals(styling.getPassword())) {
      System.out.println("암호가 일치하지 않습니다!");
      return;
    }
    StylingActionListener.inputStyle(styling, prompt);
    StylingActionListener.inputBrand(styling, prompt);
    StylingActionListener.inputFit(styling, prompt);

    stylingDao.update(styling);
  }
}
