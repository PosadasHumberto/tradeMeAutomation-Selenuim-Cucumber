package org.hposadas.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hposadas.pages.TradeMeMotorsPage;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TradeMeSteps {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    TradeMeMotorsPage page = new TradeMeMotorsPage();

    @Given("^I navigate to the TradeMe Motor page$")
    public void navigateToTradeMeMotor(){
        page.navigateToTradeMeMotor();
    }

    @Then("^I can verify that the number of car makes is (\\d+)$")
    public void returnAmountOfMakes(int makeAmount){
        int expectedAmount = makeAmount;
        int actualAmount = page.makeDropdownSize();
        Assert.assertEquals(expectedAmount, actualAmount);
    }

    @When("^I select the car make (.*)$")
    public void selectMake(String make){
        page.selectMakeFromDropdown(make);
    }

    @Then("^I can see it has (.+) records in the results$")
    public void showAmount(String expectedAmountOfRecords){
        page.clickSearch();
        Assert.assertEquals(
                "Showing " + expectedAmountOfRecords + " results",
                page.resultsAmount());
    }

    @Given("^I send the request to the endpoint$")
    public void sentGetRequest(){
        request = given().log().all().param("","");
    }

    @Then("^I can see there are (\\d+) car makes$")
    public void validateAmountOfMakes(int expectedAmount){
        response = request
                .when()
                .get("https://api.trademe.co.nz/v1/Categories/UsedCars.json");
        json = response.then().statusCode(200);
        List<String> jsonResponse = response.jsonPath().getList("Subcategories.Name");
        Assert.assertEquals("Mismatch on the expected total.", expectedAmount, jsonResponse.size());
    }
}
