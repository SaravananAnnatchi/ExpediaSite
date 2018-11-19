package Pages;

import Utilities.CommonPages;
import WebDriverProvider.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FlightBooking extends BaseClass {

    CommonPages commonPages = new CommonPages();
    String today = commonPages.getCurrentDay();
    FlightResultPage flightResultPage = new FlightResultPage();


    @FindBy(xpath = "//button[@id='tab-package-tab-hp']")
    WebElement BundleAndSave;

    @FindBy(xpath = "//button[@id='tab-flight-tab-flp']")
    WebElement Flight;

    @FindBy(xpath = "//label[contains(@id,'flight-type-roundtrip')]")
    WebElement RoundTrip;

    @FindBy(xpath = "//label[contains(@id,'flight-type-one-way-label')]") //input[contains(@id,'flight-type-one-way')]
    WebElement OneWayTrip;

    @FindBy(id = "flight-type-multi-dest-label-hp-flight")
    WebElement MultiCity;

    @FindBy(xpath = "//input[contains(@class,'gcw-distinct-locations')][contains(@id,'flight-origin')]")
    WebElement FlyingFrom;

    @FindBy(xpath = "//form[contains(@id,'gcw-flights-form')]//div[@class='autocomplete-dropdown']")
    WebElement fromAutocomplete;

    @FindBy(xpath = "//input[@aria-owns= 'typeaheadDataPlain'][contains(@id,'flight-destination')]")
    WebElement FlyingTo;

    @FindBy(xpath = "//form[contains(@id,'gcw-flights-form')]/div[3]/div[2]/div/div[2]")
    WebElement toAutocomplete;

    @FindBy(xpath = "//input[contains(@id,'flight-departing')][contains(@class,'gcw-update-date-range')]")
    WebElement DepartingDate;

    @FindBy(xpath = "//input[contains(@id,'flight-departing-single')]")
    WebElement oneWayDepartingDate;

    @FindBy(xpath = "//div[@class='datepicker-cal']//following-sibling::div[2]//td")
    List<WebElement> FromDate;

    @FindBy(xpath = "//input[starts-with(@id,'flight-returning')][@data-gcw-storeable-name='gcw-end-date']")
    WebElement ReturnDate;

    @FindBy(xpath = "//div[@class='datepicker-cal']//div[3]//td") //div[contains(@id,'flight-returning-wrapper')]//div[contains(@class,'datepicker-dropdown')]//td
    List<WebElement> ToDate;

    @FindBy(xpath = "//div[contains(@id,'flight-returning-wrapper')]//button[2]")  //div[contains(@class,'datepicker-cal')]//button[contains(@class,'next btn')]
    WebElement nextButton;

    @FindBy(xpath = "//div[contains(@class,'datepicker-cal')]//button[contains(@class,'prev btn')]")
    WebElement previousButton;

    @FindBy(xpath = "//div[@class='datepicker-close']//button[@type='button']")
    WebElement closeCalender;

    @FindBy(xpath = "//div[contains(@id,'traveler-selector')]//button[@data-gcw-single-room='true']")
    WebElement NoOfPassengers;

    @FindBy(xpath = "//div[@id=\"traveler-selector-flp\"]//span[@class = 'inline-amount-titles']")
    WebElement totalNoOfPassenger;

    @FindBy(xpath = "//div[@id='traveler-selector-flp']//li/div/div/div/div[1]/div[4]/button[contains(@class,input-plus)]")
    WebElement AdultIncreseButton;

    @FindBy(xpath = "//div[@id='traveler-selector-flp']//li/div/div/div/div[1]/div[2]/button[contains(@class,input-minus)]")
    WebElement AdultDecreseButton;

    @FindBy(xpath = "//div[@id=\"traveler-selector-flp\"]//li/div/div/div/div[1]/div[3]/span")
    WebElement noOfAdults;

    @FindBy(xpath = "//div[@id=\"traveler-selector-flp\"]//div[contains(@class,'children-wrapper')]//button[contains(@class,'uitk-step-input-minus')]")
    WebElement ChildrenDecreseButton;

    @FindBy(xpath = "//div[@id=\"traveler-selector-flp\"]//div[contains(@class,'children-wrapper')]//button[contains(@class,'uitk-step-input-plus')]")
    WebElement ChildrenIncreseButton;

    @FindBy(xpath = "//*[@id=\"traveler-selector-flp\"]//div[2]/div[1]/div[3]/span")
    WebElement noOfChildren;

    @FindBy(xpath = "//select[contains(@id,'flight-age-select-1')]")
    WebElement childAge;

    @FindBy(xpath = "//*[@id=\"traveler-selector-flp\"]//div[3]/div[1]/div[3]/span")
    WebElement noOfInfants;

    @FindBy(xpath = "//div[@id=\"traveler-selector-flp\"]//div[contains(@class,'infants-wrapper')]//button[contains(@class,'uitk-step-input-minus')]")
    WebElement InfantDecreseButton;

    @FindBy(xpath = "//div[@id=\"traveler-selector-flp\"]//div[contains(@class,'infants-wrapper')]//button[contains(@class,'uitk-step-input-plus')]")
    WebElement InfantIncreseButton;

    @FindBy(xpath = "//span[text()='Infant 1 age']//following-sibling::select")
    WebElement infantAge;

    @FindBy(xpath = "//li[@class='open']//footer[@class='menu-footer']//button[@type='button']")
    WebElement CloseNoOfPassenger;

    @FindBy(xpath = "//form[contains(@id,'gcw-flights-form')]//button[@type='submit']")
    WebElement SubmitButton;

    public FlightBooking() {
        PageFactory.initElements(driver, this);
    }


    public String getPageTitle(){
        return driver.getTitle();
    }

    public boolean verifyDefaultSelectedMenu(){
       return BundleAndSave.isDisplayed();
    }

    public boolean clickOnflight(){

   //     driver.findElement(By.xpath("//*[@id=\"tab-flight-tab-hp\"]")).click();
       Flight.click();
        boolean test = RoundTrip.isSelected();
        return test;
    }

    public boolean selectOneWay(){
        OneWayTrip.click();
        boolean selectedOneWayButton = OneWayTrip.isSelected();
        return selectedOneWayButton;
    }

    public void enterDepartureCity(String from) {
        FlyingFrom.isDisplayed();
        explicitWaitClickable(FlyingFrom);
        FlyingFrom.click();
        FlyingFrom.sendKeys(from);
        explicitWaitelementPresent(fromAutocomplete);
        FlyingFrom.sendKeys(Keys.DOWN);
        FlyingFrom.sendKeys(Keys.RETURN);
    }

    public void enterArrivalCity(String to) {
        FlyingTo.isDisplayed();
        FlyingTo.click();
        FlyingTo.sendKeys(to);
        explicitWaitelementPresent(toAutocomplete);
        FlyingTo.sendKeys(Keys.DOWN);
        FlyingTo.sendKeys(Keys.RETURN);
    }

    public void selectFromDate(){
        if(DepartingDate.isDisplayed()) {
            DepartingDate.click();
        }else if(oneWayDepartingDate.isDisplayed()){
            oneWayDepartingDate.click();
        }
            int Dates = FromDate.size();
            for (int i = 0; i < Dates; i++) {
                String date = FromDate.get(i).getText();

                if (date.contains("20")) {
                    FromDate.get(i).isEnabled();
                    FromDate.get(i).click();
                    break;
                }
            }
    }

    public void selectToDate(){
        ReturnDate.isDisplayed();
        ReturnDate.click();
        nextButton.isEnabled();
        nextButton.click();
        int Dates1 = ToDate.size();
        for(int i=0; i< Dates1; i++){
            String date = ToDate.get(i).getText();
            if(date.contains("10")){
                ToDate.get(i).isEnabled();
                ToDate.get(i).click();
                break;
            }
        }
    }


    public void selectNoOfAdults(){
        NoOfPassengers.click();
        boolean adultdec =AdultDecreseButton.isEnabled();
        if(!adultdec){
            Assert.assertTrue(AdultIncreseButton.isEnabled());
            AdultIncreseButton.click();
            Assert.assertTrue(AdultDecreseButton.isEnabled());
            AdultDecreseButton.click();
            AdultIncreseButton.click();
            int adultSelected = Integer.parseInt(noOfAdults.getText());
            Assert.assertEquals(2,adultSelected);
        }
    }

    public void selectNoOfChild(){
        boolean childdec =ChildrenDecreseButton.isEnabled();
        if(!childdec){
            Assert.assertTrue(ChildrenIncreseButton.isEnabled());
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(ChildrenIncreseButton));
            ChildrenIncreseButton.click();
            Assert.assertTrue(ChildrenDecreseButton.isEnabled());
            ChildrenDecreseButton.click();
            ChildrenIncreseButton.click();
            int childrenSelected = Integer.parseInt(noOfChildren.getText());
            Assert.assertEquals(1,childrenSelected);
            Select dropdown = new Select(childAge);
            WebElement option = dropdown.getFirstSelectedOption();
            String defaultAgeSelected = option.getText();
            Assert.assertEquals("Age",defaultAgeSelected);
            dropdown.selectByIndex(4);
            WebElement option1 = dropdown.getFirstSelectedOption();
            String selectedChildAge = option1.getText();
            Assert.assertEquals("5", selectedChildAge);
        }
    }

    public void selectNoOfInfants(){
        boolean infantdec =InfantDecreseButton.isEnabled();
        if(!infantdec){
            Assert.assertTrue(InfantIncreseButton.isEnabled());
            InfantIncreseButton.click();
            Assert.assertTrue(InfantDecreseButton.isEnabled());
            InfantDecreseButton.click();
            InfantIncreseButton.click();
            int infantSelected = Integer.parseInt(noOfInfants.getText());
            Assert.assertEquals(1,infantSelected);
            Select dropdown = new Select(infantAge);
            WebElement option = dropdown.getFirstSelectedOption();
            String defaultAgeSelected = option.getText();
            Assert.assertEquals("Age",defaultAgeSelected);
            dropdown.selectByIndex(1);
            WebElement option1 = dropdown.getFirstSelectedOption();
            String selectedInfantAge = option1.getText();
            Assert.assertEquals("Under 1", selectedInfantAge);
            CloseNoOfPassenger.click();
        }
    }

    public String totalNoOfPassengersSelected(){
        String total = totalNoOfPassenger.getText();
        return total;
    }

    public FlightResultPage clickOnSubmitButton(){
        commonPages.takeScreenshot();
        SubmitButton.click();
        return new FlightResultPage();
    }

}
