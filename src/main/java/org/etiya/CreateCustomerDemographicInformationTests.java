package org.etiya;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCustomerDemographicInformationTests {
    private WebDriver webDriver;
    private JsonConfigReader configReader;

    @BeforeEach
    public void start() throws InterruptedException {
        webDriver = new ChromeDriver();
        configReader = new JsonConfigReader("src/main/resources/config.json");
        webDriver.get("http://localhost:4200/auth-login");

        String expectedUsername = configReader.getProperty("username");
        String expectedPassword = configReader.getProperty("password");

        webDriver.manage().window().maximize();
        WebElement email = webDriver.findElement(By.id(globalConstants.EMAIL));
        email.sendKeys(expectedUsername);
        WebElement password = webDriver.findElement(By.id(globalConstants.PSW));
        password.sendKeys(expectedPassword);

        WebElement loginBtn = webDriver.findElement(By.className(globalConstants.LOGIN_BTN));
        loginBtn.click();
        Thread.sleep(3000);
        webDriver.navigate().to(globalConstants.CURRENT);
    }

    @AfterEach
    public void finish() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void enteringRegisteredNationalityIDinDemographicInformationTest() throws InterruptedException {
        String expectedCustomerName = configReader.getProperty("customerName");
        String Name = configReader.getProperty("Name");
        String LastName = configReader.getProperty("lastName");
        String MotherName = configReader.getProperty("MotherName");
        String MiddleName = configReader.getProperty("MiddleName");
        String FatherName = configReader.getProperty("FatherName");
        String BirtDate = configReader.getProperty("BirtDate");
        String NationalityId = configReader.getProperty("NationalityId");

        WebElement customer = webDriver.findElement(By.cssSelector(globalConstants.CUSTOMER_NAME));
        customer.sendKeys(expectedCustomerName);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(globalConstants.SEARCH_BTN)));

        js.executeScript("arguments[0].click();", searchButton);
        Thread.sleep(3000);
        WebElement createCustomer = webDriver.findElement(By.xpath(globalConstants.CREATE_CUSTOMER_BTN));
        createCustomer.click();
        Thread.sleep(1000);
        WebElement firstName = webDriver.findElement(By.id(globalConstants.FIRST_NAME));
        firstName.sendKeys(Name);
        Thread.sleep(1000);
        WebElement lastName = webDriver.findElement(By.id(globalConstants.LAST_NAME));
        lastName.sendKeys(LastName);
        Thread.sleep(1000);
        webDriver.findElement(By.cssSelector(globalConstants.FORM_1)).click();
        js.executeScript("arguments[0].click();", webDriver.findElement(By.id(globalConstants.GENDER)));
        WebElement dropdown = webDriver.findElement(By.id(globalConstants.GENDER));
        dropdown.findElement(By.xpath(globalConstants.GENDER_2)).click();
        Thread.sleep(1000);
        WebElement motherName = webDriver.findElement(By.id(globalConstants.MOTHER_NAME));
        motherName.sendKeys(MotherName);
        Thread.sleep(1000);
        WebElement middleName = webDriver.findElement(By.id(globalConstants.MIDDLE_NAME));
        middleName.sendKeys(MiddleName);
        Thread.sleep(1000);
        WebElement birthDate = webDriver.findElement(By.id(globalConstants.BIRTDATE));
        birthDate.sendKeys(BirtDate);
        Thread.sleep(1000);
        WebElement fatherName = webDriver.findElement(By.id(globalConstants.FATHER_NAME));
        fatherName.sendKeys(FatherName);
        Thread.sleep(1000);

        WebElement nationalityId = webDriver.findElement(By.id(globalConstants.NATIONALITY_ID));
        nationalityId.sendKeys(NationalityId);
        Thread.sleep(1000);
        WebElement nextBtn = webDriver.findElement(By.className(globalConstants.NEXT_BTN));
        nextBtn.click();
        Thread.sleep(1000);


        String expectedErrorText = "This field is invalid";
        WebElement errorMessage = webDriver.findElement(By.xpath(globalConstants.ERROR_MSG));

        assertEquals(expectedErrorText, errorMessage.getText());
        Thread.sleep(2000);
        System.out.println(errorMessage.getText());


    }
}
