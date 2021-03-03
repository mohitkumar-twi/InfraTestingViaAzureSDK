package step_definitions;

import Components.*;
import Utils.ConfigReader;
import Utils.RestAssuredUtils;
import com.microsoft.azure.management.eventhub.EventHubNamespace;
import io.restassured.response.Response;
import java.util.List;

public class BaseSteps {
    public static DLTStorageAccount dltStorageAccount = new DLTStorageAccount();
    public static RestAssuredUtils restAssuredUtils = new RestAssuredUtils();
    public static KeyVault keyvault = new KeyVault();
    public static ServiceBus servicebus = new ServiceBus();
    public static Components.EventHub eventhub = new Components.EventHub();
    public static ConfigReader cf = new ConfigReader("target/classes/config.properties");
    public static int indexOfInstance;
    public static int indexOfNamespace;
    public static String boldOpen = "\033[1m";
    public static String boldClose = "\033[0m";
    public static String resourceGroup;
    public static String UUIDFile;
    public static List<String> blobContainers;
}
