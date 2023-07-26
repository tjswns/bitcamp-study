package bitcamp.myapp;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import bitcamp.myapp.vo.AutoIncrement;
import bitcamp.myapp.vo.CsvObject;
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
    loadJson("member.json", memberList, Member.class);
    loadJson("styling.json", stylingList, Styling.class);
    loadJson("acc.json", accList, Acc.class);

  }

  private void saveData() {
    saveJson("member.json", memberList);
    saveJson("styling.json", stylingList);
    saveJson("acc.json", accList);
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

  @SuppressWarnings("unchecked")
  private <T> void loadJson(String filename, List<T> list, Class<T> clazz) {
    try {
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      StringBuilder strBuilder = new StringBuilder();
      String line = null;

      while ((line = in.readLine()) != null) {
        strBuilder.append(line);
      }

      in.close();

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
      Collection<T> objects = gson.fromJson(strBuilder.toString(),
          TypeToken.getParameterized(Collection.class, clazz).getType());

      list.addAll(objects);

      Class<?>[] interfaces = clazz.getInterfaces();
      for (Class<?> info : interfaces) {
        if (info == AutoIncrement.class) {
          AutoIncrement autoIncrement = (AutoIncrement) list.get(list.size() - 1);
          autoIncrement.updateKey();
          break;
        }
      }

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void saveJson(String filename, List<? extends CsvObject> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out = new BufferedWriter(out0); // <== Decorator(장식품) 역할 수행!
o
      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
      out.write(gson.toJson(list));

      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }
}

