package bitcamp.myapp.handler;

import bitcamp.myapp.ClientApp;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;

public class AccAddListener implements AccActionListener {

  AccDao accDao;

  public AccAddListener(AccDao accDao) {
    this.accDao = accDao;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) {
    Acc acc = new Acc();
    AccActionListener.inputStyle(acc, prompt);
    AccActionListener.inputChoose(acc, prompt);
    AccActionListener.inputSize(acc, prompt);
    acc.setWriter(ClientApp.loginUser);

    accDao.insert(acc);
  }
}
