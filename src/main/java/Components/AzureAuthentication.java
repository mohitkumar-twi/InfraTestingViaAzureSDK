package Components;

import Utils.ConfigReader;
import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.management.Azure;
import com.microsoft.rest.LogLevel;

public class AzureAuthentication {
    public static Azure azure;
    ConfigReader cf = new ConfigReader("src/main/resources/config.properties");
    public AzureAuthentication() {
        try {
            ApplicationTokenCredentials credentials = new ApplicationTokenCredentials(cf.getProperty("CLIENT_ID"), cf.getProperty("TENANT_ID"), cf.getProperty("CLIENT_SECRET"), AzureEnvironment.AZURE);
            azure = Azure
                    .configure()
                    .withLogLevel(LogLevel.NONE)
                    .authenticate(credentials)
                    .withSubscription(cf.getProperty("SUBSCRIPTION"));
        } catch (Exception e) {
            System.out.println("Incorrect Credentials - Please check the Credential Info provided");
        }

    }

}
