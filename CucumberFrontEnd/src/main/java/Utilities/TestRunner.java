package Utilities;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"C:\\selenium-test\\Cucumber\\CucumberFrontEnd\\src\\test\\resources\\Feature\\Flight.Feature"},
        glue ={"StepDefinations"},monochrome = true,tags = {"@Smoke"})
public class TestRunner {
}
