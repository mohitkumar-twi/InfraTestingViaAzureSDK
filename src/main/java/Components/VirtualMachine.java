package Components;

import com.microsoft.azure.management.network.NetworkSecurityRule;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VirtualMachine extends AzureAuthentication {

    public List<com.microsoft.azure.management.compute.VirtualMachine> getAllVirtualMachine(String resourceGroup) {
        return azure.virtualMachines().listByResourceGroup(resourceGroup);
    }

    public String getOperationSystem(String resourceGroup, String virtualmachine) {
        String osType = azure.virtualMachines().getByResourceGroup(resourceGroup, virtualmachine).osType().toString();
        String osName = azure.virtualMachines().getByResourceGroup(resourceGroup, virtualmachine).instanceView().osName();
        String osVersion = azure.virtualMachines().getByResourceGroup(resourceGroup, virtualmachine).instanceView().osVersion();
        return osType + " (" + osName + " " + osVersion + ")";
    }

    public String getPublicIP(String resourceGroup, String virtualmachine) {
        return azure.virtualMachines().getByResourceGroup(resourceGroup, virtualmachine).getPrimaryPublicIPAddress().ipAddress();
    }

    public String getPrivateIP(String resourceGroup, String virtualmachine) {
        return azure.virtualMachines().getByResourceGroup(resourceGroup, virtualmachine).getPrimaryNetworkInterface().primaryPrivateIP().toString();
    }

    public String getSize(String resourceGroup, String virtualmachine) {
        return azure.virtualMachines().getByResourceGroup(resourceGroup, virtualmachine).size().toString();
    }


    public static void main(String[] args) {
        AzureAuthentication az = new AzureAuthentication();
        String resourceGroup = "Test_ResourceGroup-VodQa";
        String virtualmachine = "Test-VirtualMachine-VodQa";

        Map<String, NetworkSecurityRule> defaultSecurityRules = azure.virtualMachines().getByResourceGroup(resourceGroup, virtualmachine).getPrimaryNetworkInterface().getNetworkSecurityGroup().defaultSecurityRules();
        Iterator<String> iterator2 = defaultSecurityRules.keySet().iterator();
        System.out.println("<-------Security Rules-------->");
        while (iterator2.hasNext()) {
            String securityRule = iterator2.next();
            System.out.println("Name- "+securityRule);
            System.out.println("Access- " +defaultSecurityRules.get(securityRule).access().toString());
            System.out.println("Priority- "+defaultSecurityRules.get(securityRule).priority());
        }
        System.out.println("<-------Security Rules-------->\n\n");

        System.out.println("Computer Name- " + azure.virtualMachines().getByResourceGroup(resourceGroup, virtualmachine).computerName());

    }


}
