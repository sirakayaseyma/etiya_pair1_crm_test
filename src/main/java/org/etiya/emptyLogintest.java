package org.etiya;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class emptyLogintest {
    private WebDriver webDriver;

    @BeforeEach
    public void setUp() throws InterruptedException {
        webDriver = new ChromeDriver();
        webDriver.get(globalConstants.BASE_URL);
        Thread.sleep(1000);
    }

    @AfterEach
    public void finish() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
    @ParameterizedTest
    @MethodSource("loginData")
    public void loginTest(String username, String password,String expectedMessage) throws InterruptedException {
        WebElement emailInput = webDriver.findElement(By.id(globalConstants.EMAIL));
        emailInput.sendKeys(username);


        WebElement passwordInput = webDriver.findElement(By.id(globalConstants.PSW));
        passwordInput.sendKeys(password);

        WebElement eyeIcon = webDriver.findElement(By.cssSelector(globalConstants.EYE2));
        eyeIcon.click();
        Thread.sleep(1000);

        WebElement eyeIcon2 = webDriver.findElement(By.cssSelector(globalConstants.EYE));
        eyeIcon2.click();
        Thread.sleep(1000);

        WebElement rememberMe = webDriver.findElement(By.id(globalConstants.REMEMBER));
        rememberMe.click();


        WebElement loginBtn = webDriver.findElement(By.className(globalConstants.LOGIN_BTN));
        loginBtn.click();
        Thread.sleep(1000);

        WebElement message = webDriver.findElement(By.cssSelector("." + globalConstants.ERROR.replace(" ", ".")));
        String messageText = message.getText();
        System.out.println(messageText);
    }

    private static Stream<Object[]> loginData() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray users = (JSONArray) parser.parse(new FileReader("src/main/resources/empty.json"));

        return users.stream().map(user -> {
            JSONObject userData = (JSONObject) user;
            String username = (String) userData.get("username");
            String password = (String) userData.get("password");
            String expectedMessage = (String) userData.get("expectedMessage");
            return new Object[]{username, password,expectedMessage};
        });
    }
}
