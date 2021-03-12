package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        plugin= {"pretty","html:target/site/cucumber-pretty","json:target/cucumber/cucumber.json"},
        tags = {"@InfraConfig"},
        monochrome = true)

public class RunTest {

}

