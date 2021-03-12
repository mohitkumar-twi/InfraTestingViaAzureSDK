package Components;

import com.microsoft.azure.management.storage.implementation.ListContainerItemInner;

import java.util.ArrayList;
import java.util.List;

public class StorageAccount extends AzureAuthentication {
    public List<com.microsoft.azure.management.storage.StorageAccount> getAllStorageAccount(String resourceGroup) {
      return  azure.storageAccounts().listByResourceGroup(resourceGroup);
    }

    public List<String> getcontainers(String resourceGroup, String storageAccount) {
        List<ListContainerItemInner> containers = azure.storageAccounts().getByResourceGroup(resourceGroup, storageAccount).manager().inner().blobContainers().list(resourceGroup, storageAccount);
        List<String> containerNames = new ArrayList<>();
        for(int i = 0 ; i<containers.size() ; i++){
            containerNames.add(containers.get(i).name());
        }
        return containerNames;

    }









    public static void main(String[] args) {
        AzureAuthentication az = new AzureAuthentication();
        String resourceGroup = "Test_ResourceGroup-VodQa";
        String storageAccount = "teststorageaccvodqa";
        System.out.println(azure.storageAccounts().getByResourceGroup(resourceGroup, storageAccount).manager().inner().blobContainers().list(resourceGroup, storageAccount).get(0).publicAccess().toString());
        System.out.println(azure.storageAccounts().getByResourceGroup(resourceGroup, storageAccount).manager().inner().blobContainers().list(resourceGroup, storageAccount).get(0).name());
    }

}
