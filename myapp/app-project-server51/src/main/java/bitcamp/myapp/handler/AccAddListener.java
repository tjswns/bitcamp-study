package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class AccAddListener implements AccActionListener {

  AccDao accDao;
  DataSource ds;

  public AccAddListener(AccDao accDao, DataSource ds) {
    this.accDao = accDao;
    this.ds = ds;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Acc acc = new Acc();
    AccActionListener.inputStyle(acc, prompt);
    AccActionListener.inputChoose(acc, prompt);
    AccActionListener.inputSize(acc, prompt);
    acc.setWriter((Member) prompt.getAttribute("loginUser"));

    try {
      accDao.insert(acc);


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
