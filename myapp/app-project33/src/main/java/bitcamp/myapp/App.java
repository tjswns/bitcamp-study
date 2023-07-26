package bitcamp.myapp;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
  ArrayList<Member> memberList = new ArrayList<>();
  ArrayList<Acc> accList = new ArrayList<>();
  ArrayList<Styling> stylingList = new ArrayList<>();

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public App() {
    prepareMenu();
  }

  public static void main(String[] args) {
    new App().execute();
  }

  static void printTitle() {
    System.out.println("우리의 OOTD");
    System.out.println("----------------------------------");
  }

  public void execute() {
    printTitle();

    loadData();
    mainMenu.execute(prompt);
    saveData();

    prompt.close();
  }

  private void loadData() {
    loadMember("member.csv", memberList);
    loadStyling("styling.csv", stylingList);
    loadAcc("acc.csv", accList);

  }

  private void saveData() {
    saveMember("member.csv", memberList);
    saveStyling("styling.csv", stylingList);
    saveAcc("acc.csv", accList);
  }

  private void prepareMenu() {
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
  }

  private void loadMember(String filename, List<Member> list) {
    try {
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      String line = null;

      while ((line = in.readLine()) != null) {
        String[] values = line.split(",");
        Member member = new Member();
        member.setNo(Integer.parseInt(values[0]));
        member.setName(values[1]);
        member.setEmail(values[2]);
        member.setPassword(values[3]);
        member.setAge(values[4]);
        member.setGender(values[5].charAt(0));
        member.setTop(values[6]);
        member.setPants(values[7]);
        member.setShoes(values[8]);
        memberList.add(member);
      }
      if (list.size() > 0) {
        // 데이터를 로딩한 이후에 추가할 회원의 번호를 설정한다.
        Member.userId = memberList.get(memberList.size() - 1).getNo() + 1;
      }
      in.close();

    } catch (Exception e) {
      System.out.println("회원 정보를 읽는 중 오류 발생!");
    }
  }

  private void loadStyling(String filename, List<Styling> list) {
    try {
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      String line = null;

      while ((line = in.readLine()) != null) {
        String[] values = line.split(",");
        Styling styling = new Styling();
        styling.setNo(Integer.parseInt(values[0]));
        styling.setStyle(values[1]);
        styling.setBrand(values[2]);
        styling.setFit(values[3]);
        styling.setPassword(values[4]);
        styling.setViewCount(Integer.parseInt(values[5]));
        styling.setCreatedDate(Long.parseLong(values[6]));
        list.add(styling);
      }

      if (list.size() > 0) {
        Styling.stylingNo = Math.max(Styling.stylingNo, list.get(list.size() - 1).getNo() + 1);
      }
      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void loadAcc(String filename, List<Acc> list) {
    try {
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      String line = null;

      while ((line = in.readLine()) != null) {
        String[] values = line.split(",");

        Acc acc = new Acc();
        acc.setNo(Integer.parseInt(values[0]));
        acc.setSelect(values[1]);
        acc.setStyle(values[2]);
        acc.setSize(values[3]);
        acc.setPassword(values[4]);
        acc.setViewCount(Integer.parseInt(values[5]));
        acc.setCreatedDate(Long.parseLong(values[6]));
        list.add(acc);
      }
      if (list.size() > 0) {
        Acc.accNo = Math.max(Acc.accNo, list.get(list.size() - 1).getNo() + 1);
      }
      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void saveMember(String filename, List<Member> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0); // <== Decorator(장식품) 역할 수행!
      PrintWriter out = new PrintWriter(out1); // <== Decorator(장식품) 역할 수행!


      for (Member member : memberList) {
        out.printf("%d,%s,%s,%s,%s,%c,%s,%s,%s\n", member.getNo(), member.getName(),
            member.getEmail(), member.getPassword(), member.getAge(), member.getGender(),
            member.getTop(), member.getPants(), member.getShoes());
      }
      out.close();

    } catch (Exception e) {
      System.out.println("회원 정보를 저장하는 중 오류 발생!");
    }
  }

  private void saveStyling(String filename, List<Styling> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0); // <== Decorator(장식품) 역할 수행!
      PrintWriter out = new PrintWriter(out1); // <== Decorator(장식품) 역할 수행!

      for (Styling styling : list) {
        out.printf("%d,%s,%s,%s,%s,%d,%d\n", styling.getNo(), styling.getStyle(),
            styling.getBrand(), styling.getFit(), styling.getPassword(), styling.getViewCount(),
            styling.getCreatedDate());
      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }

  private void saveAcc(String filename, List<Acc> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0); // <== Decorator(장식품) 역할 수행!
      PrintWriter out = new PrintWriter(out1); // <== Decorator(장식품) 역할 수행!

      for (Acc acc : list) {
        out.printf("%d,%s,%s,%s,%s,%d,%d\n", acc.getNo(), acc.getSelect(), acc.getStyle(),
            acc.getSize(), acc.getPassword(), acc.getViewCount(), acc.getCreatedDate());
      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }
}

