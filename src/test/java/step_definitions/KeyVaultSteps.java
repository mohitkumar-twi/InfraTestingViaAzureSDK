package step_definitions;

import com.microsoft.azure.management.keyvault.Vault;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class KeyVaultSteps extends BaseSteps {
    public static int indexOfKeyVault;
    public static List<Vault> keys;
    public static String resourceGroup;

    @When("^I capture all the keyvaults from the \"([^\"]*)\" resource group$")
    public void i_capture_all_the_keyvaults_from_the_resource_group(String ResourceGroup) throws Exception {
        resourceGroup = ResourceGroup;
        keys = keyvaultObject.getallKeyVault(resourceGroup);
        System.out.println("List of All KeyVaults Present under "+resourceGroup+" Resource Group - "+keys);
    }

    @Then("^I validate if the \"([^\"]*)\" keyvault  is present$")
    public void i_validate_if_the_keyvault_is_present(String Vault) throws Exception {
        boolean isKeyVaultPresent = false;
        for (int i = 0; i < keys.size(); i++) {
            System.out.println("Key Vault - "+keys.get(i).name());
            if (keys.get(i).name().equals(Vault)) {
                isKeyVaultPresent = true;
                indexOfKeyVault = i;
            }
        }
        Assert.assertEquals("Key Vault " + Vault + " is not present.", true, isKeyVaultPresent);

    }

    @Then("^I validate below Properties of \"([^\"]*)\" keyvault$")
    public void i_validate_below_Properties_of_keyvault(String keyVault, Map<String, String> properties) throws Exception {
        Iterator<String> iterator2 = properties.keySet().iterator();
        while (iterator2.hasNext()) {
            String property = iterator2.next();
            switch (property) {
                case "Tags":
                    break;
                case "Soft-delete":
                    boolean isSoftDeleteEnabled = keyvaultObject.isSoftdeleteEnabled(keyVault, resourceGroup);
                    System.out.println("Is Soft Delete Enabled - " + isSoftDeleteEnabled);

                    if (properties.get("Soft-delete").equals("Enabled")) {
                        Assert.assertEquals("Soft-delete should be Enabled", true, isSoftDeleteEnabled);
                    } else {
                        Assert.assertEquals("Soft-delete should be disabled", false, isSoftDeleteEnabled);
                    }
                    break;

                case "Purge protection":
                    boolean isPurgeProtectionEnabled = keyvaultObject.isPurgeProtectionEnabled(keyVault, resourceGroup);
                    System.out.println("Is Purge Protection Enabled - " + isPurgeProtectionEnabled);
                    if (properties.get("Purge protection").equals("Enabled")) {
                        Assert.assertEquals("Purge Protection should be Enabled", true, isPurgeProtectionEnabled);
                    } else {
                        Assert.assertEquals("Purge Protection should be disabled", false, isPurgeProtectionEnabled);
                    }
                    break;
            }

        }

    }

}


