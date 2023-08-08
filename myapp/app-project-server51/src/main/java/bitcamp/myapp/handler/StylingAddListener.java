package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class StylingAddListener implements StylingActionListener {

  StylingDao stylingDao;
  DataSource ds;

  public StylingAddListener(StylingDao stylingDao, DataSource ds) {
    this.stylingDao = stylingDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Styling styling = new Styling();
    StylingActionListener.inputStyle(styling, prompt);
    StylingActionListener.inputBrand(styling, prompt);
    StylingActionListener.inputFit(styling, prompt);
    styling.setWriter((Member) prompt.getAttribute("loginUser"));

    try {
      stylingDao.insert(styling);

      ds.getConnection().commit();

    } catch (Exception e) {
      try {
        ds.getConnection().rollback();
      } catch (Exception e2) {
      }
      throw new RuntimeException(e);
    }
  }
}
