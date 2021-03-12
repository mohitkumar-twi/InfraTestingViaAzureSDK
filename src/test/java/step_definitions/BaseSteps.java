package step_definitions;

import Components.StorageAccount;
import Components.KeyVault;
import Components.VirtualMachine;
import Utils.ConfigReader;

import java.util.List;

public class BaseSteps {
    public static StorageAccount storageAccountObject = new StorageAccount();
    public static KeyVault keyvaultObject = new KeyVault();
    public static VirtualMachine virtualMachineObject = new VirtualMachine();

    public static ConfigReader cf = new ConfigReader("src/main/resources/config.properties");
    public static int indexOfInstance;
    public static int indexOfNamespace;
    public static String boldOpen = "\033[1m";
    public static String boldClose = "\033[0m";
    public static String resourceGroup;
    public static String UUIDFile;
    
    public static List<String> blobContainers;
}
