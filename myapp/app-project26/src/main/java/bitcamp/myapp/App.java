package bitcamp.myapp;

import java.util.ArrayList;
import bitcamp.myapp.handler.AccAddListener;
import bitcamp.myapp.handler.AccDeleteListener;
import bitcamp.myapp.handler.AccDetailListener;
import bitcamp.myapp.handler.AccListListener;
import bitcamp.myapp.handler.AccUpdateListener;
import bitcamp.myapp.handler.FooterListener;
import bitcamp.myapp.handler.HeaderListener;
import bitcamp.myapp.handler.HelloListener;
import bitcamp.myapp.handler.MemberAddListener;
import bitcamp.myapp.handler.MemberDeleteListener;
import bitcamp.myapp.handler.MemberDetailListener;
import bitcamp.myapp.handler.MemberListListener;
import bitcamp.myapp.handler.MemberUpdateListener;
import bitcamp.myapp.handler.StylingAddListener;
import bitcamp.myapp.handler.StylingDeleteListener;
import bitcamp.myapp.handler.StylingDetailListener;
import bitcamp.myapp.handler.StylingListListener;
import bitcamp.myapp.handler.StylingUpdateListener;
import bitcamp.myapp.vo.Acc;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;

public class App {

  public static void main(String[] args) {

    ArrayList<Member> memberList = new ArrayList<>();
    ArrayList<Acc> accList = new ArrayList<>();
    ArrayList<Styling> stylingList = new ArrayList<>();

    BreadcrumbPrompt prompt = new BreadcrumbPrompt();

    MenuGroup mainMenu = new MenuGroup("메인");

    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberList)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberList)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberList)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberList)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberList)));
    mainMenu.add(memberMenu);

    MenuGroup stylingMenu = new MenuGroup("스타일");
    stylingMenu.add(new Menu("등록", new StylingAddListener(stylingList)));
    stylingMenu.add(new Menu("목록", new StylingListListener(stylingList)));
    stylingMenu.add(new Menu("조회", new StylingDetailListener(stylingList)));
    stylingMenu.add(new Menu("변경", new StylingUpdateListener(stylingList)));
    stylingMenu.add(new Menu("삭제", new StylingDeleteListener(stylingList)));
    mainMenu.add(stylingMenu);

    MenuGroup accMenu = new MenuGroup("악세사리");
    accMenu.add(new Menu("등록", new AccAddListener(accList)));
    accMenu.add(new Menu("목록", new AccListListener(accList)));
    accMenu.add(new Menu("조회", new AccDetailListener(accList)));
    accMenu.add(new Menu("변경", new AccUpdateListener(accList)));
    accMenu.add(new Menu("삭제", new AccDeleteListener(accList)));
    mainMenu.add(accMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);

    printTitle();

    mainMenu.execute(prompt);

    prompt.close();
  }


  static void printTitle() {
    System.out.println("회원별 옷 사이즈 관리 시스템");
    System.out.println("----------------------------------");
  }
}
