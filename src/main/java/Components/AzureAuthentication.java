package Components;

import Utils.ConfigReader;
import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.management.Azure;
import com.microsoft.rest.LogLevel;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class AzureAuthentication {
    public static Azure azure;
    ConfigReader cf = new ConfigReader("target/classes/config.properties");
    private static Logger logger = Logger.getLogger(AzureAuthentication.class);

    public AzureAuthentication() {
        try {

            ApplicationTokenCredentials credentials = new ApplicationTokenCredentials(cf.getProperty("CLIENT_ID"),cf.getProperty("TENANT_ID"),cf.getProperty("CLIENT_SECRET"), AzureEnvironment.AZURE);
            azure = Azure
                    .configure()
                    .withLogLevel(LogLevel.NONE)
                    .authenticate(credentials)
                    .withDefaultSubscription();
        } catch (IOException e) {
            logger.info("Incorrect Credentials - Please check the Credential Info provided");
        }


    }

}
