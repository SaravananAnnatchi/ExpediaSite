package Pages;

import Utilities.CommonPages;
import WebDriverProvider.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightCheckout extends BaseClass {

    @FindBy(xpath ="//select[@id='title[0]']")
    WebElement title;

    @FindBy(xpath ="//input[@id='firstname[0]']")
    WebElement firstName;

    @FindBy(xpath ="//input[@id='lastname[0]']")
    WebElement lastName;

    @FindBy(xpath ="//select[@id='country_code[0]']")
    WebElement country;

    @FindBy(xpath ="//input[@id='phone-number[0]']")
    WebElement phoneNumber;

    @FindBy(xpath ="//input[@id='confirmation-sms-provider-checkbox']")
    WebElement alertCheckBox;

    @FindBy(xpath ="//select[@id='passport[0]']")
    WebElement passport;

    @FindBy(xpath ="//button[@id='optional-prefs-toggle-link-0']")
    WebElement moreOptions;

    @FindBy(xpath ="//input[@id='no_insurance']")
    WebElement insuranceRadioButton;

    public FlightCheckout() {
        PageFactory.initElements(driver, this);
    }

    CommonPages commonPages;

    public String isAt(){
        pageLoadTimeout();
        implicitWait();
        return driver.getTitle();
    }

    public void selectTitle(String Title){
        commonPages = new CommonPages();
        title.isDisplayed();
        commonPages.selectDropdown(title,Title);
    }

    public void entername(String Firstname, String Lastname){
        firstName.isDisplayed();
        firstName.isEnabled();
         firstName.sendKeys(Firstname);
        lastName.isDisplayed();
        lastName.isEnabled();
        lastName.sendKeys(Lastname);
    }

    public void selectCountry(String Country){
        country.isDisplayed();
        country.isEnabled();
        commonPages.selectDropdown(country,Country);
    }

    public void enterPhoneNumber(String PhoneNumber){
        phoneNumber.isDisplayed();
        phoneNumber.isEnabled();
        phoneNumber.sendKeys(PhoneNumber);
    }

    public void checkAlertCheckbox(){
        alertCheckBox.isEnabled();
        alertCheckBox.isSelected();
    }

    public void enterPassportNumber(String Passport){
        passport.isDisplayed();
        passport.isEnabled();
        passport.sendKeys(Passport);
    }

    public void clickOnMoreOptions(){
        moreOptions.isDisplayed();
        moreOptions.isEnabled();
        moreOptions.click();
        moreOptions.click();
    }

    public void selectInsurance(){
        insuranceRadioButton.isDisplayed();
        insuranceRadioButton.isEnabled();
        insuranceRadioButton.click();
    }


}
