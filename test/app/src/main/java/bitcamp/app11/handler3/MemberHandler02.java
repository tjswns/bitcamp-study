package bitcamp.app11.handler3;

import bitcamp.app11.util3.Prompt02;
import bitcamp.app11.vo3.Member02;

public class MemberHandler02 {

  static final int MAX_SIZE = 100;
  static Member02[] members = new Member02[MAX_SIZE];
  static int userId = 1;
  static int length = 0;

  static final char MALE = 'M';
  static final char FEMALE = 'W';

  public static void inputMember() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Member02 m = new Member02();
    m.name = Prompt02.inputString("이름? ");
    m.age = Prompt02.inputString("나이? ");
    m.gender = inputGender((char)0);

    if (m.gender == MALE) {
    loop: while (true) {
      String menuNo = Prompt02.inputString("상의:\n" + 
      "  1. M\n" + 
      "  2. L\n" +
      "  3. XL\n" + 
      "> ");

      switch (menuNo) {
          case "1":
            m.top = "M";
            break loop;
          case "2":
            m.top = "L";
            break loop;
          case "3":
            m.top = "XL";
            break loop;
          default:
            System.out.println("무효한 번호입니다.");
        }
    }

    loop: while (true) {
      String menuNo = Prompt02.inputString("바지:\n" + 
      "  1. 28\n" + 
      "  2. 30\n" +
      "  3. 32\n" + 
      "> ");

      switch (menuNo) {
          case "1":
            m.pants = "28";
            break loop;
          case "2":
            m.pants = "30";
            break loop;
          case "3":
            m.pants = "32";
            break loop;
          default:
            System.out.println("무효한 번호입니다.");
        }
      }

      loop: while (true) {
        String menuNo = Prompt02.inputString("신발:\n" + 
       "  1. 260\n" + 
       "  2. 265\n" +
       "  3. 270\n" + 
       "> ");

       switch (menuNo) {
           case "1":
             m.shoes = "260";
             break loop;
           case "2":
             m.shoes = "265";
             break loop;
           case "3":
             m.shoes = "270";
             break loop;
           default:
             System.out.println("무효한 번호입니다.");
         }
       }
      } else if (m.gender == FEMALE) {

        loop: while (true) {
          String menuNo = Prompt02.inputString("상의:\n" + 
          "  1. XS\n" + 
          "  2. S\n" +
          "  3. M\n" + 
          "> ");
  
          switch (menuNo) {
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
            }
        }
        loop: while (true) {
          String menuNo = Prompt02.inputString("치마:\n" +
          " 1. 24\n" +
          " 2. 26\n" +
          " 3. 28\n" +
          "> ");
          switch (menuNo) {
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
          }
      }
      loop: while (true) {
        String menuNo = Prompt02.inputString("신발:\n" + 
        "  1. 240\n" + 
        "  2. 245\n" +
        "  3. 250\n" + 
        "> ");
    
        switch (menuNo) {
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
      }
    }
    m.no = userId++;

    members[length++] = m;
  }

  public static void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 상의, 하의, 신발, 성별");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      Member02 m = members[i];
      System.out.printf("%d, %s, %s, %s, %s, %s, %s\n", 
        m.no, m.name, m.age, m.top, m.pants, m.shoes,
        toGenderString(m.gender));
      }
    }

    public static void viewMember() {
      String memberNo = Prompt02.inputString("번호? ");
      for (int i = 0; i < length; i++) {
        Member02 m = members[i];
        if (m.no == Integer.parseInt(memberNo)) {
          System.out.printf("이름: %s\n", m.name);
          System.out.printf("나이: %s\n", m.age);
          System.out.printf("성별: %s\n", toGenderString(m.gender));
          System.out.printf("상의: %s\n", m.top);
          System.out.printf("바지: %s\n", m.pants);
          System.out.printf("신발: %s\n", m.shoes);
          return;
        }
      }
      System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  public static void updateMember() {
    String memberNo = Prompt02.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member02 m = members[i];
      if (m.no == Integer.parseInt(memberNo)) {
        System.out.printf("이름(%s)? ", m.name);
        m.name = Prompt02.inputString("");
        System.out.printf("나이(%s)? ", m.age);
        m.age = Prompt02.inputString("");
        m.gender = inputGender(m.gender);
        if (m.gender == MALE) {
          loop: while (true) {
            String menuNo = Prompt02.inputString("상의:\n" + 
            "  1. M\n" + 
            "  2. L\n" +
            "  3. XL\n" + 
            "> ");
      
            switch (menuNo) {
                case "1":
                  m.top = "M";
                  break loop;
                case "2":
                  m.top = "L";
                  break loop;
                case "3":
                  m.top = "XL";
                  break loop;
                default:
                  System.out.println("무효한 번호입니다.");
              }
          }
      
          loop: while (true) {
            String menuNo = Prompt02.inputString("바지:\n" + 
            "  1. 28\n" + 
            "  2. 30\n" +
            "  3. 32\n" + 
            "> ");
      
            switch (menuNo) {
                case "1":
                  m.pants = "28";
                  break loop;
                case "2":
                  m.pants = "30";
                  break loop;
                case "3":
                  m.pants = "32";
                  break loop;
                default:
                  System.out.println("무효한 번호입니다.");
              }
            }
      
            loop: while (true) {
              String menuNo = Prompt02.inputString("신발:\n" + 
             "  1. 260\n" + 
             "  2. 265\n" +
             "  3. 270\n" + 
             "> ");
      
             switch (menuNo) {
                 case "1":
                   m.shoes = "260";
                   break loop;
                 case "2":
                   m.shoes = "265";
                   break loop;
                 case "3":
                   m.shoes = "270";
                   break loop;
                 default:
                   System.out.println("무효한 번호입니다.");
               }
             }
            } else if (m.gender == FEMALE) {
      
              loop: while (true) {
                String menuNo = Prompt02.inputString("상의:\n" + 
                "  1. XS\n" + 
                "  2. S\n" +
                "  3. M\n" + 
                "> ");
        
                switch (menuNo) {
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
                  }
              }
              loop: while (true) {
                String menuNo = Prompt02.inputString("치마:\n" +
                " 1. 24\n" +
                " 2. 26\n" +
                " 3. 28\n" +
                "> ");
                switch (menuNo) {
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
                }
            }
            loop: while (true) {
              String menuNo = Prompt02.inputString("신발:\n" + 
              "  1. 240\n" + 
              "  2. 245\n" +
              "  3. 250\n" + 
              "> ");
          
              switch (menuNo) {
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
            }
          }
        }
    }
  }

  private static char inputGender(char gender) {
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }
    loop: while (true) {
      String menuNo = Prompt02.inputString(label + 
      "  1. 남자\n" + 
      "  2. 여자\n" + 
      "> ");

      switch (menuNo) {
        case "1":
          return MALE;
        case "2":
          return FEMALE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public static void deleteMember() {
    int memberNo = Prompt02.inputInt("번호? ");

    int deletedIndex = indexOf(memberNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      members[i] = members[i + 1];
    }

    members[--length] = null;
  }

  private static int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      Member02 m = members[i];
      if (m.no == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() {
    return length < MAX_SIZE;
  }
}