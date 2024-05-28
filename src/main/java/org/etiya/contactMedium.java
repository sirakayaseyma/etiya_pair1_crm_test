package org.etiya;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class contactMedium {
    WebDriver webDriver;
    private JsonConfigReader contactReader;
    @BeforeEach
    public void start() {
        contactReader = new JsonConfigReader("src/main/resources/contact.json");
        String username = contactReader.getProperty("username");
        String password = contactReader.getProperty("password");
        webDriver = new ChromeDriver();
        webDriver.get(globalConstants.BASE_URL);
        WebElement emailInput = webDriver.findElement(By.id(globalConstants.EMAIL));
        emailInput.sendKeys(username);
        WebElement passwordInput = webDriver.findElement(By.id(globalConstants.PSW));
        passwordInput.sendKeys(password);
        WebElement rememberMe = webDriver.findElement(By.id(globalConstants.REMEMBER));
        rememberMe.click();
        WebElement loginBtn = webDriver.findElement(By.className(globalConstants.LOGIN_BTN));
        loginBtn.click();
        webDriver.manage().window().maximize();
    }
    @AfterEach
    public void end() {
        webDriver.quit();
    }
    @Test
    public void titleTest() {
        WebElement title = webDriver.findElement(By.className(globalConstants.SUB));
        String expectedResult = "Contact Medium";
        Assertions.assertEquals(expectedResult, title.getText());
    }
    @Test
    public void pageSourceTest() {
        String text = webDriver.getPageSource();
        System.out.println(text);
    }
    @Test
    public void buttonControlTest() {
        WebElement cancelButton = webDriver.findElement(By.className(globalConstants.CANCEL));
        cancelButton.click();
        WebElement nextButton = webDriver.findElement(By.className(globalConstants.NEXT));
        nextButton.click();
    }

    @Test
    public void successfulContactMediumTest() throws InterruptedException {
        Thread.sleep(2000);
        webDriver.navigate().to(globalConstants.CONTACT);
        String email = contactReader.getProperty("email");
        String mobilePhone = contactReader.getProperty("mobilePhone");
        String homePhone = contactReader.getProperty("homePhone");
        String fax = contactReader.getProperty("fax");


        WebElement emailInput = webDriver.findElement(By.id(globalConstants.EMAIL2));
        emailInput.sendKeys(email);
        WebElement mobilePhoneInput = webDriver.findElement(By.id(globalConstants.MOBILE));
        mobilePhoneInput.sendKeys(mobilePhone);
        WebElement homePhoneInput = webDriver.findElement(By.id(globalConstants.HOME));
        homePhoneInput.sendKeys(homePhone);
        WebElement faxInput = webDriver.findElement(By.id(globalConstants.FAX));
        faxInput.sendKeys(fax);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.className(globalConstants.NEXT)));
        createButton.click();
    }
    @Test
    public void validContactMediumTest() throws InterruptedException {

        String email = contactReader.getProperty("email");
        String mobilePhone = contactReader.getProperty("mobilePhone");
        String homePhone = contactReader.getProperty("homePhone");
        String fax = contactReader.getProperty("fax");
        Thread.sleep(2000);
        webDriver.navigate().to(globalConstants.CONTACT);
        Thread.sleep(2000);
        WebElement emailInput = webDriver.findElement(By.id(globalConstants.EMAIL2));
        emailInput.sendKeys(email);
        WebElement mobilePhoneInput = webDriver.findElement(By.id(globalConstants.MOBILE));
        mobilePhoneInput.sendKeys(mobilePhone);
        WebElement homePhoneInput = webDriver.findElement(By.id(globalConstants.HOME));
        homePhoneInput.sendKeys(homePhone);
        WebElement faxInput = webDriver.findElement(By.id(globalConstants.FAX));
        faxInput.sendKeys(fax);
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.className(globalConstants.NEXT)));
        createButton.click();
    }

    @Test
    public void contactMediumTest() throws InterruptedException {
        Thread.sleep(2000);
        webDriver.navigate().to(globalConstants.CONTACT);
        Thread.sleep(2000);
        String email = contactReader.getProperty("email");
        String mobilePhone = contactReader.getProperty("mobilePhone");

        WebElement emailInput = webDriver.findElement(By.id(globalConstants.EMAIL2));
        emailInput.sendKeys(email);
        WebElement mobilePhoneInput = webDriver.findElement(By.id(globalConstants.MOBILE));
        mobilePhoneInput.sendKeys(mobilePhone);
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement createButton = wait.until
                (ExpectedConditions.elementToBeClickable(By.className(globalConstants.NEXT)));
        createButton.click();
    }

    @Test
    public void invalidEmailTest() {

        String email2 = contactReader.getProperty("email2");

        WebElement emailInput = webDriver.findElement(By.id(globalConstants.EMAIL2));
        emailInput.sendKeys(email2);
        webDriver.findElement(By.id(globalConstants.MOBILE)).click();
        WebElement errorMessage =webDriver.findElement(By.xpath(globalConstants.CONTACTERROR));
        System.out.println(errorMessage);
        String expectedResult = " This field is required ";
        Assertions.assertEquals(expectedResult, errorMessage.getText());
    }
    @Test
    public void invalidMobilePhoneTest() {
        String email3 = contactReader.getProperty("email3");
        WebElement emailInput = webDriver.findElement(By.id(globalConstants.EMAIL2));
        emailInput.sendKeys(email3);
        webDriver.findElement(By.id(globalConstants.MOBILE)).click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.className(globalConstants.NEXT)));
        createButton.click();
        WebElement errorMessage =webDriver.findElement(By.xpath(globalConstants.CONTACTERROR2));
        System.out.println(errorMessage);
        String expectedResult = " This field is required ";
        Assertions.assertEquals(expectedResult, errorMessage.getText());
    }
}
 