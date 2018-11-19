package WebDriverProvider;

import cucumber.api.java.After;

public class WebDriverClose extends BaseClass {

    @After
    public void closeBrowser(){
        driver.quit();
    }
}
