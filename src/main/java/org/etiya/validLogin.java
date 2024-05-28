package org.etiya;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class validLogin {
    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.get(globalConstants.BASE_URL);
    }

    @AfterEach
    public void finish() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void loginTest() throws InterruptedException {
        WebElement emailInput = webDriver.findElement(By.id(globalConstants.EMAIL));
        emailInput.sendKeys("test");

        WebElement passwordInput = webDriver.findElement(By.id(globalConstants.PSW));
        passwordInput.sendKeys("test");

        WebElement rememberMe = webDriver.findElement(By.id(globalConstants.REMEMBER));
        rememberMe.click();
        Thread.sleep(2000);

        WebElement loginBtn = webDriver.findElement(By.className(globalConstants.LOGIN_BTN));
        loginBtn.click();
        Thread.sleep(5000);

    }

}
