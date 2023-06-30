package bitcamp.App12.handler;

import bitcamp.App12.util.Prompt;

public class aa {



  loop:while(true)
  {
    String menuNo = Prompt.inputString("신발:\n" + "  1. 260\n" + "  2. 265\n" + "  3. 270\n" + "> ");

    switch (menuNo) {
      case "1":
        m.Shoes = "260";
        break loop;
      case "2":
        m.Shoes = "265";
        break loop;
      case "3":
        m.Shoes = "270";
        break loop;
      default:
        System.out.println("무효한 번호입니다.");
    }
  }}else if(m.gender==FEMALE){

  loop:while(true){
  String menuNo = Prompt.inputString("상의:\n" + "  1. XS\n" + "  2. S\n" + "  3. M\n" + "> ");

  switch(menuNo)
  {
        case "1":
          m.top = "XS";
          break loop;
        case "2":
          m.top = "S";
          break loop;
        case "3":
          m.top = "M";
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }}loop:while(true){
  String menuNo =
      Prompt.inputString("치마:\n" + " 1. 24\n" + " 2. 26\n" + " 3. 28\n" + "> ");switch(menuNo)
  {
        case "1":
          m.pants = "24";
          break loop;
        case "2":
          m.pants = "26";
          break loop;
        case "3":
          m.pants = "28";
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }}loop:while(true){
  String menuNo = Prompt.inputString("신발:\n" + "  1. 240\n" + "  2. 245\n" + "  3. 250\n" + "> ");

  switch(menuNo)
  {
        case "1":
          m.shoes = "240";
          break loop;
        case "2":
          m.shoes = "245";
          break loop;
        case "3":
          m.shoes = "250";
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
}}}
