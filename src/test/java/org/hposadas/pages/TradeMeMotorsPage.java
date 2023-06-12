package org.hposadas.pages;


public class TradeMeMotorsPage extends BasePage{

    private String url = "https://www.trademe.co.nz/a/motors";
    private String makeDropdown = "/html[1]/body[1]/tm-root[1]/div[1]/main[1]/div[1]/tm-motors-home-page[1]/tm-motors-home-page-header[1]/div[1]/div[1]/tm-motors-search-form[1]/form[1]/tm-motors-used-cars-large[1]/tg-row[1]/tg-col[2]/tg-select-container[1]/div[1]/select[1]";
    private String searchButton = "//button[contains(text(),'Search')]";
    private String resultsLabel = "/html[1]/body[1]/tm-root[1]/div[1]/main[1]/div[1]/tm-motors-search-results[1]/div[1]/div[1]/div[1]/div[1]/tm-search-header-result-count[1]/h3[1]";

    public TradeMeMotorsPage() {
        super(driver);
    }

    public void navigateToTradeMeMotor(){
        navigateTo(url);
    }

    public int makeDropdownSize(){
        return dropdownSize(makeDropdown);
    }

    public void selectMakeFromDropdown(String make){
        selectFromDropdownByText(makeDropdown, make);
    }

    public void clickSearch() {
        clickElement(searchButton);
    }

    public String resultsAmount() {
        return textFromElement(resultsLabel);
    }

}
