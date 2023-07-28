package bitcamp.myapp.handler;

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
    AccActionListener.inputSelect(acc, prompt);
    AccActionListener.inputStyle(acc, prompt);
    AccActionListener.inputSize(acc, prompt);
    acc.setPassword(prompt.inputString("암호? "));
    accDao.insert(acc);
  }
}
