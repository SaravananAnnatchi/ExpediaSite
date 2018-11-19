package Utilities;

import WebDriverProvider.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonPages extends BaseClass {

    public String getCurrentDay(){
        Calendar calender = Calendar.getInstance(TimeZone.getDefault());
        int todayInt = calender.get(Calendar.DAY_OF_MONTH);
        String todayStr = Integer.toString(todayInt);
        return todayStr;
    }

    public String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

    public void takeScreenshot(){
        try{
        File sourcefile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourcefile, new File ("C:\\selenium-test\\Screenshot\\FlightBoking"+ UUID.randomUUID()+"_"+timestamp()+".png"));
    } catch (IOException e){
            e.getStackTrace();
        }
    }

    public void selectDropdown(WebElement element, String visibletext){
        explicitWaitClickable(element);
        Select select = new Select(element);
        select.selectByVisibleText(visibletext);
    }

    public void slider(WebElement element){
        Actions action = new Actions(driver);
        action.dragAndDropBy(element, 100, 0);
    }

    public void closeSecondWindow(){
        Set<String> handler = driver.getWindowHandles();
        Iterator<String> iterate = handler.iterator();
        String parentWindowId = iterate.next();
        while(iterate.hasNext()){
            String childWindowId = iterate.next();
            driver.switchTo().window(childWindowId);
            driver.close();
            driver.switchTo().window(parentWindowId);
        }
    }

    public void switchToSecondWindowAndCloseThirdWindow(){
        Set<String> handler = driver.getWindowHandles();
        Iterator<String> iterate = handler.iterator();
        String parentWindowId = iterate.next();
        while(iterate.hasNext()){
            String firstChildWindowId = iterate.next();
            while(iterate.hasNext()){
                String secondChildWindowId = iterate.next();
                driver.switchTo().window(firstChildWindowId);
                driver.close();
            }
            driver.switchTo().window(firstChildWindowId);
        }
    }

    public void switchToSecondWindow(){
        Set<String> handler = driver.getWindowHandles();
        Iterator<String> iterate = handler.iterator();
        String parentWindowId = iterate.next();
        while(iterate.hasNext()){
            String childWindowId = iterate.next();
            driver.switchTo().window(childWindowId);
        }
    }
}
