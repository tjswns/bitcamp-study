package bitcamp.app;

public class App01 {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App01().getGreeting());
    }
}
