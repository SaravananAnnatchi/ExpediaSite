package Pages;

import Utilities.CommonPages;
import WebDriverProvider.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FlightResultPage extends BaseClass {

    @FindBy(xpath ="//div[@id='flight-listing-container']//li")
    List<WebElement> totalResultsFound;

    @FindBy(id ="sortDropdown")
    WebElement sortDropdown;

    @FindBy(xpath ="//input[contains(@id,'stopFilter_stops')]")
    List<WebElement> stops;

    @FindBy(xpath ="//input[@id='stopFilter_stops-1']")
    WebElement oneStopCheckBox;

    @FindBy(xpath ="//div[@id='flight-listing-container']//span[contains(@class,'number-stops')]")
    List<WebElement> numberOfStops;

    @FindBy(xpath ="//div[@id='flight-listing-container']//span[contains(@class,'full')]")
    List<WebElement> price;

    @FindBy(xpath ="//button[contains(@class, 'btn-secondary btn-action')]")
    List<WebElement> selectFlightButton;

    @FindBy(xpath ="//h3[@class='dark medium-bold secondary-content marginless']")
    WebElement departureFlightSelected;




    public FlightResultPage() {
        PageFactory.initElements(driver, this);
    }

    CommonPages commonPages = new CommonPages();

    public String isAt(){
        pageLoadTimeout();
        implicitWait();
        return driver.getTitle();
    }

    public String getFromAndTo(){
        String split = driver.getTitle();
        String[] afterSplit = split.split("to");
        String fromFlight = afterSplit[0].trim();
        String[] remainingValue = afterSplit[1].split("Flights");
        String toFlight = remainingValue[0].trim();
        return fromFlight+" to "+toFlight;
    }

    public int totalNoOfResultsReturned(){
        int totalresult = totalResultsFound.size();
        return totalresult;
    }

    public void verifyNoOfStopcheckbox(){
        for(int i= 0;i <stops.size();i++) {
            stops.get(i).isDisplayed();
            boolean checked = stops.get(i).isSelected();
            if (!checked) {
                if (totalNoOfResultsReturned()!= 0) {
                    stops.get(i).click();
                    commonPages.closeSecondWindow();
                    verifyNumberOfStopsResult();
                    commonPages.takeScreenshot();
                    commonPages.slider(oneStopCheckBox);
                    stops.get(i).click();
                }else{
                    System.out.println("No Results found");
                }
            } else System.out.println("Checkbox is already checked");
        }
    }


    public void verifyNumberOfStopsResult(){
        ArrayList<String> Stops = new ArrayList();

        for(int i=0;i<numberOfStops.size();i++){
            String stop = numberOfStops.get(i).getText();
            Stops.add(stop);

            switch(stop) {
                case "(Nonstop)":
                    Assert.assertEquals("(Nonstop)", stop);
                    break;
                case "(1 stop)":
                    Assert.assertEquals("(1 stop)", stop);
                    break;
                default:
                    System.out.println("Non exist stops are found");
                    break;
            }

       }
  //      for(WebElement stop: numberOfStops){
  //          String text = stop.getText();
  //        Stops.add(text);

  //          switch(text){
  //              case "(Nonstop)":
  //                  Assert.assertEquals("(Nonstop)" ,text);
  //                  break;
  //             case "(1 stop)":
  //                 Assert.assertEquals("(1 stop)", text);
  //                 break;
  //              default:
  //                 System.out.println("Non exist stops are found");
  //                 break;
   //        }
   //     }
   //     System.out.println("The Stops are " +Stops);
    }



    public void pricesorting(String SortOrder) throws InterruptedException {
        ArrayList<Integer> pricesort = new ArrayList<>();
        if (totalNoOfResultsReturned()!= 0) {
            for (int i = 0; i < price.size(); i++) {
                Thread.sleep(500);
                String pricesorting = price.get(i).getText();
                int priceint = Integer.parseInt(pricesorting.replace("$", ""));
                pricesort.add(priceint);
                for (int rowIndex = 0; rowIndex < pricesort.size() - 1; rowIndex++) {
                    verifySortingInt(SortOrder, pricesort.get(rowIndex), pricesort.get(rowIndex + 1));
                }
            }
        }else{
            System.out.println("No Results found");
        }

        System.out.println("All the prices listed are : " +pricesort +"\n");
    }

    public void sortDropdowncheck(String dropdownvalue){
        if (totalNoOfResultsReturned()!= 0) {
            if(sortDropdown.isEnabled()) {
                commonPages.selectDropdown(sortDropdown, dropdownvalue);
            }
        }else{
            System.out.println("No Results found");
        }
    }

    public void verifySortingInt(String sortorder, int prev, int next) {
        if (sortorder.equals("Price (Lowest)")) {
            Assert.assertTrue("Price is sorted by lowest", prev <= next);
        } else if (sortorder.equals("Price (Highest)")) {
            Assert.assertTrue("Prices are sorted by highest", prev >= next);
        }
    }

    public String selectDepartureFlight() throws InterruptedException {
        int flightToSelect = Math.min(1,selectFlightButton.size());
        pageLoadTimeout();
        Thread.sleep(7000);
    //    explicitWaitAllElementPresent(selectFlightButton);
        for(int i=0; i <flightToSelect;i++) {
            commonPages.closeSecondWindow();
            if(selectFlightButton.get(i).isEnabled()) {
                selectFlightButton.get(i).click();
                commonPages.closeSecondWindow();
                return departureFlightSelected.getText();
            }
        }
        return null;
    }

    public FlightCheckoutReview selectArrivalFlight() throws InterruptedException {
        int flightToSelect = Math.min(1,selectFlightButton.size());
        Thread.sleep(5000);
  //      explicitWaitAllElementPresent(selectFlightButton);
        for(int i=0; i <flightToSelect;i++) {
            if(selectFlightButton.get(i).isEnabled()) {
                selectFlightButton.get(i).click();
                commonPages.switchToSecondWindowAndCloseThirdWindow();
                return new FlightCheckoutReview();
            }
        }
        return null;
    }


}
