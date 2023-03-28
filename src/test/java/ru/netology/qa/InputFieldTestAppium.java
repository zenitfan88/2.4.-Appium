package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainScreen;

import java.net.MalformedURLException;
import java.net.URL;


public class InputFieldTestAppium {

    private AndroidDriver driver;
    private String emptyText = "       ";
    private String text = "zxcasdqwe";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void emptyInputFieldTest() {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.textToBeChanged.isDisplayed();
        String actual = mainScreen.textToBeChanged.getText();
        mainScreen.userInput.sendKeys(emptyText);
        mainScreen.buttonChange.isDisplayed();
        mainScreen.buttonChange.click();
        String expected = mainScreen.textToBeChanged.getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inputActivityTest() throws InterruptedException {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.userInput.sendKeys(text);
        mainScreen.buttonActivity.isDisplayed();
        mainScreen.buttonActivity.click();
        mainScreen.textActivity.isDisplayed();
        String expected = mainScreen.textActivity.getText();
        Assertions.assertEquals(expected, text);
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}