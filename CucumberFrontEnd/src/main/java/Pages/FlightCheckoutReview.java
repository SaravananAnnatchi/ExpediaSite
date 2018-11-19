package Pages;

import WebDriverProvider.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FlightCheckoutReview extends BaseClass {

    @FindBy(xpath ="//div[@class='alert-title type-300']")
    WebElement reviewText;

    @FindBy(xpath ="//button[@id='bookButton']")
    WebElement checkoutButton;

    public FlightCheckoutReview() {
        PageFactory.initElements(driver, this);
    }

    public String isAt(){
        pageLoadTimeout();
        implicitWait();
        return driver.getTitle();
    }

    public boolean messageValidation(){
        return reviewText.getText().contains("Nice Job");
    }

    public FlightCheckout clickOnCheckoutButton(){
        checkoutButton.isDisplayed();
        checkoutButton.click();
        return new FlightCheckout();
    }




}
