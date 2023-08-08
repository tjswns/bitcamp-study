package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class AccAddListener implements AccActionListener {

  AccDao accDao;

  public AccAddListener(AccDao accDao) {
    this.accDao = accDao;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Acc acc = new Acc();
    AccActionListener.inputStyle(acc, prompt);
    AccActionListener.inputChoose(acc, prompt);
    AccActionListener.inputSize(acc, prompt);
    acc.setWriter((Member) prompt.getAttribute("loginUser"));

    accDao.insert(acc);
  }
}
