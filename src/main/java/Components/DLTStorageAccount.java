package Components;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobContainerItem;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.models.DeleteSnapshotsOptionType;
import com.microsoft.azure.management.resources.GenericResource;
import com.microsoft.azure.management.storage.StorageAccount;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.*;

public class DLTStorageAccount extends AzureAuthentication {
    private static Logger logger = Logger.getLogger(Components.DLTStorageAccount.class);
    private static String DLT_Log_Storage_Container_Name;
    private static String DLT_Storage_Account_URL;
    private static String DLT_Storage_Connection_String;
    private static String Test_Data_Path = "src/test/resources/data/tmp";


    public BlobContainerClient getDLTBlobContainerClient(String accountURL, String containerName, String connectionString) {
        DLT_Storage_Account_URL = accountURL;
        DLT_Log_Storage_Container_Name = containerName;
        DLT_Storage_Connection_String = connectionString;
        BlobContainerClient blobContainerClient = new BlobContainerClientBuilder()
                .endpoint(DLT_Storage_Account_URL)
                .connectionString(DLT_Storage_Connection_String)
                .containerName(DLT_Log_Storage_Container_Name)
                .buildClient();
        logger.info("Blob Container Client has been constructed");
        return blobContainerClient;
    }


    public void uploadDLTLogFile(String logFileName, BlobContainerClient blobContainerClient) {
        try {
            BlobClient blobClient = blobContainerClient.getBlobClient(logFileName);
            Map<String, String> metadata = new HashMap<String, String>();
            metadata.put("Properties", "{\"mode\":\"live\",\"deviceClusterID\":\"Testing\",\"deviceClusterName\":\"Testing\",\"tenant\":\"CIVIC\",\"vin\":\"Testing\",\"Filename\":\""+logFileName+"\"}");
            blobClient.uploadFromFile(Test_Data_Path + "/" + logFileName);
            logger.info("Log File " + logFileName + " has been uploaded to DLT Storage Account");
            blobClient.setMetadata(metadata);
            logger.info("Metadata added with the File - " + metadata);
        } catch (com.azure.storage.blob.models.BlobStorageException e) {
            logger.info("Blob with name " + logFileName + " already exists.");
            logger.info("Parser Service will not be triggered ");
        }
    }

    public void deleteDLTLogFile(String logFileName, BlobContainerClient blobContainerClient) {
        BlobClient blobClient = blobContainerClient.getBlobClient(logFileName);
        blobClient.delete();
        logger.info("DLT Log File " + logFileName + " has been deleted");
    }

    public void getAllBlobs(BlobContainerClient blobContainerClient) {
        System.out.println(blobContainerClient.listBlobs());
    }


    public List<StorageAccount> getAllStorageAccounts(String resourceGroup) throws IOException {
        return azure.storageAccounts().listByResourceGroup(resourceGroup);

    }

    public List<GenericResource> getAllGenericResources(String resourceGroup) {
        return azure.genericResources().listByResourceGroup(resourceGroup);
    }

    public static List<String> getListOfBlobContainers(String connectionString) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        List<String> blobContainers = new ArrayList<String>();
        for (BlobContainerItem item : blobServiceClient.listBlobContainers()) {
            blobContainers.add(item.getName());
        }
        System.out.println("Blob Containers - " + blobContainers);
        return blobContainers;
    }

    public String getComponentperEnvironment(String componentName) {
        String env = cf.getProperty("ENVIRONMENT");
        String compName = componentName.replace("env", env);
        return compName;
    }

    public String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid;


    }

    public BlobContainerClient getContainerClient(String storage_Account_ConnectionString , String containerName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(storage_Account_ConnectionString).buildClient();
        BlobContainerClient blobcontainerclient = blobServiceClient.getBlobContainerClient(containerName);
        return blobcontainerclient;
    }

        public void getLastUploadedFileName(BlobContainerClient blobContainerClient, String filePath) {
        List<OffsetTime> uploadTimes = new ArrayList<OffsetTime>();
        for (BlobItem bItem : blobContainerClient.listBlobsByHierarchy(filePath)) {
            System.out.println(bItem.getName());
        }
        Collections.sort(uploadTimes);
        Collections.reverse(uploadTimes);
        System.out.println(uploadTimes);
    }


}
