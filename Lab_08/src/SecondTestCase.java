import org.testng.annotations.Test;

public class SecondTestCase {

    @Test(priority = 1)
    void setup() {
        System.out.println("SecondTestCase setup");
    }

    @Test
    void login() {
        System.out.println("SecondTestCase login");
    }

    @Test
    void down() {
        System.out.println("SecondTestCase down");
    }
}
