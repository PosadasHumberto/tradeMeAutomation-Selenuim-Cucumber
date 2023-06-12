package org.hposadas.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.hposadas.pages.BasePage;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "org/hposadas/steps"/*,
        plugin = {"json.target/cucumber.json"}*/
)
public class Runner {

    @AfterClass
    public static void classDriver() {
        BasePage.closeNavigator();
    }
}
