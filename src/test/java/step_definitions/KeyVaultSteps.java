package step_definitions;

import com.microsoft.azure.management.keyvault.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.apache.log4j.Logger;
import java.util.List;


public class KeyVaultSteps extends BaseSteps {

    private static Logger logger = Logger.getLogger(KeyVaultSteps.class);
    public static int indexOfKeyVault;
    public static List<Vault> keys;

    @When("^i capture all the keyvault from the \"([^\"]*)\" resource group$")
    public void i_capture_all_the_keyvault_from_the_resource_group(String resourceGroup) throws Exception {
        keys = keyvault.getallKeyVault(resourceGroup);
    }

    @Then("^i validate if the \"([^\"]*)\" keyvault  is present$")
    public void i_validate_if_the_keyvault_is_present(String Vault) throws Exception {
        boolean isKeyVaultPresent = false;
        String actualKeyVaultName = keyvault.getComponentperEnvironment(Vault);
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).name().equals(actualKeyVaultName)) {
                isKeyVaultPresent = true;
                indexOfKeyVault = i;
            }
        }
        logger.info("Index of " + keys.get(indexOfKeyVault).name() + " is " + indexOfKeyVault);
        Assert.assertEquals("Key Vault " + actualKeyVaultName + " is not present.", true, isKeyVaultPresent);

    }
}
