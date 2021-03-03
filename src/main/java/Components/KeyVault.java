package Components;

import java.util.List;

import com.microsoft.azure.management.keyvault.Secret;
import com.microsoft.azure.management.keyvault.Vault;

public class KeyVault extends AzureAuthentication {

    public List<Vault> getallKeyVault(String resourceGroup) throws Exception {
        List<Vault> Keys = azure.vaults().listByResourceGroup(resourceGroup);
        return Keys;
    }

    public String getComponentperEnvironment(String componentName) {
        String env = cf.getProperty("ENVIRONMENT");
        String compName = componentName.replace("env", env);
        return compName;
    }

    public String getKeyvaultSecret(String secretName, String resourceGroup, String keyVaultName) {
        String secretValue = "";
        for (Vault vault : azure.vaults().listByResourceGroup(resourceGroup)) {
            System.out.println(vault.name());
            if (vault.name().equals(keyVaultName)) {
                secretValue = vault.secrets().getByName(secretName).value();
            }
        }
        return secretValue;
    }
}

