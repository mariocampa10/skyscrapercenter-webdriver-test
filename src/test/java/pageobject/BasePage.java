package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    private final int timeout = 30;


    public void selectByValue(WebElement webElement, String value) {
        waitForVisibility(webElement);
        Select select = new Select(webElement);
        select.selectByValue(value);
        waitForPageLoaded();
    }

    public void waitForVisibility(WebElement webElement) {
        waitForCondition(ExpectedConditions.visibilityOf(webElement), timeout);
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        waitForConditionBoolean(jsLoad, timeout);
    }

    private void waitForCondition(ExpectedCondition<WebElement> condition, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    private boolean waitForConditionBoolean(ExpectedCondition<Boolean> condition, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(condition);
    }
}
