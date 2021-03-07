package step_definitions;

import Components.AzureAuthentication;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;


public class Hooks extends BaseSteps {

    public static Logger logger = Logger.getLogger(Hooks.class);

    @Before
    public void authenticationForInfraTesting(Scenario scenario) {

        AzureAuthentication azureAuth = new AzureAuthentication();

    }


    @After
    public void clearData(Scenario scenario) {

    }

}
