package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Listeners;
import util.GlobalVariables;
import util.Helpers;

public class CalendarHomePage extends Helpers {

    protected IOSDriver driver;

    @iOSXCUITFindBy(accessibility = "Calendar")
    private RemoteWebElement calendarHomePageContainer;

    public CalendarHomePage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Calendar home page is loaded")
    public boolean calendarHomePageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(calendarHomePageContainer)).isDisplayed();
    }

    @Step("Timeslot {0} is chosen")
    public void chooseTimeslot(String timeslot) {
        WebElement element = driver.findElement(AppiumBy.accessibilityId(timeslot));
        scrollTo(driver, element, Directions.DOWN, 5);
        longPress(driver, element);
    }
}
