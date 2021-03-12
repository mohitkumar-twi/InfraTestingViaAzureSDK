package Components;

import com.microsoft.azure.management.keyvault.Vault;

import java.util.List;
import java.util.Map;

public class KeyVault extends AzureAuthentication {

    public List<Vault> getallKeyVault(String resourceGroup) throws Exception {
        List<Vault> Keys = azure.vaults().listByResourceGroup(resourceGroup);
        return Keys;
    }

    public Map<String,String> getTags(String keyVaultName, String resourceGroup){
        return azure.vaults().getByResourceGroup(resourceGroup,keyVaultName).tags();
    }

    public boolean isSoftdeleteEnabled(String keyVaultName,String resourceGroup){
        return azure.vaults().getByResourceGroup(resourceGroup,keyVaultName).softDeleteEnabled();
    }

    public boolean isPurgeProtectionEnabled(String keyVaultName,String resourceGroup){
        return azure.vaults().getByResourceGroup(resourceGroup,keyVaultName).purgeProtectionEnabled();
    }


    public static void main(String[] args){
        AzureAuthentication az =  new AzureAuthentication();
        String resourceGroup="Test_ResourceGroup-VodQa";
        String keyvault = "Test-KeyVault-VodQa";

    }
}

