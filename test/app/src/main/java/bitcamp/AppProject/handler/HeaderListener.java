package bitcamp.AppProject.handler;

import bitcamp.AppProject.util.ActionListener;
import bitcamp.AppProject.util.BreadcrumbPrompt;

public class HeaderListener implements ActionListener {
  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("=============================[비트캠프!]==");
  }
}
