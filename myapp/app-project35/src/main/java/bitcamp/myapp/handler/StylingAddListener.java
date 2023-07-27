package bitcamp.myapp.handler;

import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;

public class StylingAddListener implements StylingActionListener {

  StylingDao stylingDao;

  public StylingAddListener(StylingDao stylingDao) {
    this.stylingDao = stylingDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Styling styling = new Styling();
    StylingActionListener.inputStyle(styling, prompt);
    StylingActionListener.inputBrand(styling, prompt);
    StylingActionListener.inputFit(styling, prompt);
    styling.setPassword(prompt.inputString("암호? "));
    stylingDao.insert(styling);
  }
}
