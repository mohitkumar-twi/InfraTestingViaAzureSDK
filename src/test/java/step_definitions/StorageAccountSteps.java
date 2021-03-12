
package step_definitions;

import com.microsoft.azure.management.storage.StorageAccount;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

public class StorageAccountSteps extends BaseSteps {
    public static String resourceGroup;
    public static List<StorageAccount> storageAccounts;

    @When("^I capture all StorageAccount in \"([^\"]*)\" resource group$")
    public void i_capture_all_StorageAccount_in_resource_group(String ResourceGroup) throws Exception {
        resourceGroup = ResourceGroup;
        storageAccounts = storageAccountObject.getAllStorageAccount(resourceGroup);
    }

    @Then("^I validate that \"([^\"]*)\" account exist$")
    public void i_validate_that_account_exist(String storageAccountName) throws Exception {
        boolean isStorageAccountPresent = false;
        for (int i = 0; i < storageAccounts.size(); i++) {
            System.out.println("Storage Account - "+storageAccounts.get(i).name());
            if (storageAccounts.get(i).name().equals(storageAccountName)) {
                isStorageAccountPresent = true;
            }
        }
        Assert.assertEquals("Storage Account " + storageAccountName + " is not present.", true, isStorageAccountPresent);
    }
    @Then("^I validate that below containers are present in the \"([^\"]*)\" storage Account$")
    public void i_validate_that_below_containers_are_present_in_the_storage_Account(String storageAccountName, List<String> containerNames) throws Exception {
        List<String> actualContainerNames = storageAccountObject.getcontainers(resourceGroup,storageAccountName);
        System.out.println("Containers Present in "+storageAccountName+" storage account - "+actualContainerNames);
        Assert.assertEquals("These containers are actually present",containerNames,actualContainerNames);
    }


}

