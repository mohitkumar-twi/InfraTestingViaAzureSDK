package step_definitions;

import Components.AzureAuthentication;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class Hooks extends BaseSteps {

    @Before
    public void authenticationForInfraTesting(Scenario scenario) {

        AzureAuthentication azureAuth = new AzureAuthentication();

    }

    @After
    public void clearData(Scenario scenario) {

    }

}
