package pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarTaxCheckHomePage {

    WebDriver driver;

    public CarTaxCheckHomePage(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(driver, this);
       
    }

    @FindBy(id = "vrm-input")
    private WebElement vrmInputTextBox;

    @FindBy(xpath = "//button[text()='Free Car Check']")
    private WebElement freeCheck;


    public void enterCarRegistrationNumber(String regNumber) throws InterruptedException {
        vrmInputTextBox.clear();
        vrmInputTextBox.sendKeys(regNumber);
        freeCheck.click();
    }

    public void openCarTaxWebSite(){
        driver.navigate().to("https://cartaxcheck.co.uk/");
    }
}
