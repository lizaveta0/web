import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.OrderPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCardTest {
    public static WebDriver driver;
    private static OrderPage orderPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        orderPage = new OrderPage(driver);
        driver.get("http://localhost:9999/");

    }

    @Test
    public void testOrderFlowPositive() {
        String name = "Тестовый Пользователь";
        String phone = "+79998887766";
        Boolean accept = true;

        orderPage.fillOrderForm(name, phone, accept);
        String successMessage = orderPage.getOrderSuccessHeader();

        assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", successMessage);
    }

    @Test
    public void testOrderFlowNegativeName() {
        String name = "Negative User";
        String phone = "+79998887766";
        Boolean accept = true;

        orderPage.fillOrderForm(name, phone, accept);
        String errorForName = orderPage.getErrorFofName();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", errorForName);
    }

    @Test
    public void testOrderFlowNegativePhone() {
        String name = "Тестовый Пользователь";
        String phone = "+7999888776";
        Boolean accept = true;

        orderPage.fillOrderForm(name, phone, accept);
        String errorForPhone = orderPage.getErrorForPhone();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", errorForPhone);
    }

    @Test
    public void testOrderFlowNegativeAgreement() {
        String name = "Тестовый Пользователь";
        String phone = "+79998887766";
        Boolean accept = false;

        orderPage.fillOrderForm(name, phone, accept);
        Boolean agreement = orderPage.agreementIsValid();

        assertEquals(accept, agreement);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
