package StepDefinations;

import Pages.FlightBooking;
import Pages.FlightCheckout;
import Pages.FlightCheckoutReview;
import Pages.FlightResultPage;
import WebDriverProvider.BaseClass;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class FlightBookingStepDef extends BaseClass {
    FlightBooking flightBooking;
    FlightResultPage flightResultPage;
    FlightCheckoutReview flightCheckoutReview;
    FlightCheckout flightCheckout;

    @Before
    public void setUp(){
        webDriverInitialization();
        pageLoadTimeout();
        implicitWait();
        getURL();
        flightBooking = new FlightBooking();
    }

    @Given("^User on Expedia page$")
    public void userOnExpediaPage() {

        String title = flightBooking.getPageTitle();
        Assert.assertEquals("Cheap Flights: Airline Tickets, Airfare Deals & One Way Flights | Expedia", title);
 //       boolean selected = flightBooking.verifyDefaultSelectedMenu();
 //       Assert.assertTrue(selected);
    }

    @When("^User click on Flight tab$")
    public void userClickOnFlightTab(){
       boolean defaultTabSelected = flightBooking.clickOnflight();
 //      Assert.assertTrue(defaultTabSelected);
    }


    @Then("^Enters departing city \"([^\"]*)\" and arrival city \"([^\"]*)\"$")
    public void entersDepartingCityAndArrivalCity(String FromCity, String ToCity){
        flightBooking.enterDepartureCity(FromCity);
        flightBooking.enterArrivalCity(ToCity);
    }

    @Then("^Pick the Departure Date and Return Date$")
    public void pickTheDepartureDateAndReturnDate(){
        flightBooking.selectFromDate();
        flightBooking.selectToDate();
    }

    @Then("^select number of passengers$")
    public void selectAdult() {
        flightBooking.selectNoOfAdults();
        flightBooking.selectNoOfChild();
        flightBooking.selectNoOfInfants();
        String totalNoOfPassengers = flightBooking.totalNoOfPassengersSelected();
        Assert.assertEquals(totalNoOfPassengers, "2 Adults, 2 Children");
    }

    @And("^click on Submit Button$")
    public void clickOnSubmitButton(){
        flightResultPage = flightBooking.clickOnSubmitButton();
        String result = flightResultPage.isAt();
        String actualFlightFromandTo = flightResultPage.getFromAndTo();
        Assert.assertEquals(result,actualFlightFromandTo+" Flights | Expedia");

      // String result = flightBooking.clickOnSubmitButton();
//       Assert.assertEquals(result,"STO to PAR Flights | Expedia");
    }

    @Then("^Verify the number of products returned on search result page$")
    public void verifyTheSearchResultPage() {
        flightResultPage.totalNoOfResultsReturned();
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }


    @Then("^Verify the number of stops$")
    public void verifyTheNumberOfStops(){
        flightResultPage.verifyNoOfStopcheckbox();
    }


    @Then("^Pick the Departure Date$")
    public void pickTheDepartureDate(){
        flightBooking.selectFromDate();
    }

    @And("^Click on OneWay Button$")
    public void clickOnOneWayButton(){
        flightBooking.selectOneWay();

    }

    @Then("^Verify the results sorted by \"(.*)\"$")
    public void verifyThePriceSortedByLowest(String SortOrder) throws InterruptedException {
        flightResultPage.pricesorting(SortOrder);
    }

    @Then("^select the price sorting by \"(.*)\" in the dropdown$")
    public void selectThePriceSortingByHighestInTheDropdown(String dropdownValue) {
        flightResultPage.sortDropdowncheck(dropdownValue);

    }

    @Then("^Select the flights$")
    public void selectTheFlights() throws InterruptedException {
        String departureflightselect = flightResultPage.selectDepartureFlight();
        Assert.assertEquals(departureflightselect, "Your selected departure");
        flightCheckoutReview = flightResultPage.selectArrivalFlight();
        String checkoutreviewPage = flightCheckoutReview.isAt();
        Assert.assertEquals(checkoutreviewPage, "Trip Detail | Expedia" );
        boolean messageValidate = flightCheckoutReview.messageValidation();
        Assert.assertTrue(messageValidate);
    }

    @And("^Navigate to check out page$")
    public void navigateToCheckOutPage(){
        flightCheckout = flightCheckoutReview.clickOnCheckoutButton();
        String checkoutPage = flightCheckout.isAt();
        Assert.assertEquals(checkoutPage, "Expedia: Payment");
    }

    @And("^Select the \"(.*)\"$")
    public void selectTheTitle(String Title){
        flightCheckout.selectTitle(Title);
    }

    @And("^Select the (.*) and Enter the (.*) and (.*)$")
    public void selectTheTitleAndEnterTheFirstnameAndLastname(String Title, String Firstname, String Lastname){
        flightCheckout.selectTitle(Title);
        flightCheckout.entername(Firstname, Lastname);
    }

    @And("^Select (.*) and enter (.*)$")
    public void selectCountryAndEnterPhoneNumber(String Country, String PhoneNumber){
        flightCheckout.selectCountry(Country);
        flightCheckout.enterPhoneNumber(PhoneNumber);
    }



    @And("^Uncheck the alert checkbox and Select the (.*) and click on more options$")
    public void uncheckTheAlertCheckboxAndSelectThePassportAndClickOnMoreOptions(String Passport) {
        flightCheckout.checkAlertCheckbox();
        flightCheckout.enterPassportNumber(Passport);
        flightCheckout.clickOnMoreOptions();
    }
}
