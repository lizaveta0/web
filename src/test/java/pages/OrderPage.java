package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By inputName = By.name("name");
    private By inputPhone = By.name("phone");
    private By agreemetCheck = By.xpath("//*[@data-test-id='agreement']");
    private By continueButton = By.xpath("//*[@type='button']");
    private By successHeader = By.xpath("//*[@data-test-id='order-success']");
    private By errorForName = By.xpath("//*[@data-test-id='name' and contains(@class, 'input_invalid')]//span[@class='input__sub']");
    private By errorForPhone = By.xpath("//*[@data-test-id='phone' and contains(@class, 'input_invalid')]//span[@class='input__sub']");



    public OrderPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Заполнение формы
    public void fillOrderForm(String name, String phone, Boolean accept) {
        // Заполнение полей
       driver.findElement(inputName).sendKeys(name);
       driver.findElement(inputPhone).sendKeys(phone);

       if (accept) {
           driver.findElement(agreemetCheck).click();
       }

       driver.findElement(continueButton).click();
    }

    public String getOrderSuccessHeader() {
        return getText(successHeader);
    }

    public String getErrorFofName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorForName));
        return getText(errorForName);
    }

    public String getErrorForPhone() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorForPhone));
        return getText(errorForPhone);
    }

    public boolean agreementIsValid() {
        return !driver.findElement(agreemetCheck).getAttribute("class").contains("input_invalid");
    }


    // Базовые методы
    public String getText(By element) {
        return driver.findElement(element).getText();
    }
}
