package step_definitions;

import Components.DLTStorageAccount;
import Components.KeyVault;
import Utils.ConfigReader;

import java.util.List;

public class BaseSteps {
    public static DLTStorageAccount dltStorageAccount = new DLTStorageAccount();
    public static KeyVault keyvault = new KeyVault();

    public static ConfigReader cf = new ConfigReader("src/main/resources/config.properties");
    public static int indexOfInstance;
    public static int indexOfNamespace;
    public static String boldOpen = "\033[1m";
    public static String boldClose = "\033[0m";
    public static String resourceGroup;
    public static String UUIDFile;
    public static List<String> blobContainers;
}
