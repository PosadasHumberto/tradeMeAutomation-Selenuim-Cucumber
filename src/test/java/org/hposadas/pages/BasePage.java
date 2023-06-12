package org.hposadas.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions action;

    static {
        driver = new ChromeDriver(new ChromeOptions());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement find(String locator){
        try{
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        }catch (TimeoutException e) {
            throw new NoSuchElementException("Elment not found: " + locator);
        }
    }

    public static void navigateTo(String url){
        driver.get(url);
    }

    public static void closeNavigator() {
        driver.quit();
    }

    public void clickElement(String locator) {
        find(locator).click();
    }

    public void goToLinkText(String linkTest) {
        driver.findElement(By.linkText(linkTest));
    }

    public void submitElement(String locator) {
        find(locator).submit();
    }

    public void write(String locator, String textToWrite) {
        find(locator).clear();
        find(locator).sendKeys(textToWrite);
    }

    public void selectFromDropdownByValue(String locator, String valueToSelect) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByVisibleText(valueToSelect);
    }

    public void selectFromDropdownByIndex(String locator, int valueToSelect) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByIndex(valueToSelect);
    }

    public void selectDropdownByText(String locator, String valuToSelect) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByVisibleText(valuToSelect);
    }

    public void hoverOverElement(String locator){
        action.moveToElement(find(locator));
    }

    public void doubleClick(String locator) {
        action.doubleClick(find(locator));
    }

    public void rightClick(String locator) {
        action.contextClick(find(locator));
    }

    public String getValueFromTable(String locator, int row, int column){
        String cellINeed = locator+"/table/tbody/tr["+row+"]/td["+column+"]";

        return find(cellINeed).getText();
    }

    public void setValueOnTable(String locator, int row, int column, String stringToSend){

        String cellToFill = locator+"/table/tbody/tr["+row+"]/td["+column+"]";

        find(cellToFill).sendKeys(stringToSend);
    }

    public void switchToiFrame(int iFrameIndex){
        driver.switchTo().frame(iFrameIndex);
    }

    public void switchToParentFrame(){
        driver.switchTo().parentFrame();
    }

    public void dismissAlert(){
        try{
            driver.switchTo().alert().dismiss();
        }catch(NoAlertPresentException e){
            e.printStackTrace();
        }
    }

    public String textFromElement(String locator){
        return find(locator).getText();
    }

    public boolean elementEnabled(String locator){
        return find(locator).isEnabled();
    }

    public boolean elementIsDisplayed(String locator){

        return find(locator).isDisplayed();
    }

    public boolean elementIsSelected(String locator){

        return find(locator).isSelected();
    }

    public List<WebElement> bringMeAllElements(String locator){
        return driver.findElements(By.className(locator));
    }

    public void selectNthElementFromList(String locator, int index){
        List<WebElement> list = driver.findElements(By.className(locator));
        list.get(index).click();
    }

    public void dragAndDrop(String locator, String locator2){
        WebElement element = find(locator);
        WebElement element2 = find(locator2);
        action.dragAndDrop(element, element2).build().perform();
    }

    public void selectCriteriaFromList(String locator, String criteria){
        List<WebElement> list = driver.findElements(By.className(locator));
        for(WebElement element : list){
            if(element.getText().equals(criteria)){
                element.click();
                break;
            }
        }
    }


}
