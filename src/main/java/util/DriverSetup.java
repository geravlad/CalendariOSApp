package util;

import io.qameta.allure.Step;
import org.testng.annotations.Listeners;
import pages.*;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Listeners({ITestListenerUtility.class})

public class DriverSetup extends ConfigReader{

    protected static IOSDriver driver;

    protected Helpers helpers;

    protected CalendarHomePage calendarHomePage;
    protected NewEventPage newEventPage;

    @Step("Driver is started")
    @BeforeMethod
    public void setUP() {

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(getProperty("device.name"))
                .setBundleId(getProperty("bundle.id"))
                .setPlatformVersion(getProperty("platform.version"))
                .setNoReset(false);

        try {
            driver = new IOSDriver(new URI(GlobalVariables.localAppiumServerUrl).toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        helpers = new Helpers();

        calendarHomePage = new CalendarHomePage(driver);
        newEventPage = new NewEventPage(driver);
    }

    @Step("Driver is closed")
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
