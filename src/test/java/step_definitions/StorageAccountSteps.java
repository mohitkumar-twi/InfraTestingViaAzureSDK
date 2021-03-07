/*
//TODO :
package step_definitions;

import com.azure.storage.blob.BlobContainerClient;
import com.microsoft.azure.management.resources.GenericResource;
import com.microsoft.azure.management.storage.StorageAccount;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

public class StorageAccountSteps extends BaseSteps {
    private static BlobContainerClient blobContainerClient;
    public static List<StorageAccount> storageAccounts;
    public static List<GenericResource> genericResources;
    public static String accountName;
    private static Logger logger = Logger.getLogger(StorageAccountSteps.class);
    public String environment = cf.getProperty("ENVIRONMENT");


    @When("^I go to the azure \"([^\"]*)\" resource group$")
    public void i_go_to_the_azure_resource_group(String resourceGroupName) throws IOException {
        storageAccounts = dltStorageAccount.getAllStorageAccounts(resourceGroupName);
        genericResources = dltStorageAccount.getAllGenericResources(resourceGroupName);
    }


    @Then("^I validate that \"([^\"]*)\" account exist$")
    public void i_validate_that_account_exist(String storageAccountName) {
        boolean isStorageAccountsPresent = false;
        String expectedStorageName =storageAccountName.replace("env",environment);

        for (int i = 0; i < storageAccounts.size(); i++) {
            isStorageAccountsPresent = storageAccounts.get(i).name().equals(expectedStorageName);
            if (isStorageAccountsPresent) {
                logger.info("Storage Account - "+boldOpen+storageAccounts.get(i).name()+boldClose+" is found.");
                break;
            }
        }

        Assert.assertEquals(expectedStorageName+" storage account is not present", true,isStorageAccountsPresent);
    }


    @When("^I open the \"([^\"]*)\" account$")
    public void i_open_the_account(String storageAccountName) {
        accountName = storageAccountName.replace("env",environment);
        String accountKey = "";
        for (int i = 0; i < storageAccounts.size(); i++) {
            if (storageAccounts.get(i).name().equals(accountName)) {
                accountKey = storageAccounts.get(i).getKeys().get(0).value();
                break;
            }

        }

        final String storageConnection = "DefaultEndpointsProtocol=https;"
                + "AccountName=" + storageAccountName.replace("env",environment)
                + ";AccountKey=" + accountKey
                + ";EndpointSuffix=core.windows.net";

        blobContainers = dltStorageAccount.getListOfBlobContainers(storageConnection);

    }

    @Then("^I validate that \"([^\"]*)\" container exist$")
    public void i_validate_that_container_exist(String blobContainerName) {
       // Assert.assertEquals(accountName + " should not have more than 1 Blob Containers", 1, blobContainers.size());
        if (blobContainerName.contains("env")) {
            blobContainerName = cf.getProperty("ENVIRONMENT");

            if (blobContainerName.equals("nonprod"))
                blobContainerName = "non-prod";
        }
        Assert.assertEquals("Blob Containers "+blobContainerName+" is not present in " + accountName , true, blobContainers.contains(blobContainerName));
    }


    @Then("^I validate that \"([^\"]*)\" Event Grid should also be present\\.$")
    public void i_validate_that_Event_Grid_should_also_be_present(String storageAccount){
        String actualStorageAccount = storageAccount.replace("env",environment);
        boolean isEventGridPresent= false;
        int counter = 0;
        for(int i =0 ; i<genericResources.size() ; i++){
            if(genericResources.get(i).type().equals("Microsoft.EventGrid/systemTopics")){
                    if(genericResources.get(i).name().contains(actualStorageAccount)){
                        logger.info("Event Grid System Topic - "+boldOpen+genericResources.get(i).name()+boldClose+" is found.");
                        if(counter==0){
                        isEventGridPresent = true;
                        }
                        else{
                            isEventGridPresent = false;
                        }
                        counter++;
                    }

            }
        }

        Assert.assertEquals("Event Grid System Topic - "+storageAccount+" is not present.",true,isEventGridPresent);

    }
    @When("^I Upload DLT Log File \"([^\"]*)\" to DLT Storage Account$")
    public void i_Upload_DLT_Log_File_to_DLT_Storage_Account(String logFileName) throws InterruptedException {
        blobContainerClient = dltStorageAccount.getDLTBlobContainerClient();
        String UUID = java.util.UUID.randomUUID().toString();
        //UUIDFile = dltStorageAccount.storingFileToTMP(logFileName,UUID);
        dltStorageAccount.uploadDLTLogFile(UUIDFile, blobContainerClient);
        Thread.sleep(30000);
    }

    @Then("^I Delete the DLT Log File \"([^\"]*)\" from the DLT Storage Account$")
    public void i_Delete_the_DLT_Log_File_from_the_DLT_Storage_Account(String logFileName) {
        blobContainerClient = dltStorageAccount.getDLTBlobContainerClient();

        dltStorageAccount.deleteDLTLogFile(UUIDFile, blobContainerClient);
    }

}
*/
