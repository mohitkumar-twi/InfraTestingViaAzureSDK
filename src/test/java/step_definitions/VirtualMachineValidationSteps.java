package step_definitions;

import com.microsoft.azure.management.compute.VirtualMachine;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VirtualMachineValidationSteps extends BaseSteps {
    public static String resourceGroup;
    public static List<VirtualMachine> vm;

    @When("^I capture all VMs in \"([^\"]*)\" resource group$")
    public void i_capture_all_VMs_in_resource_group(String ResourceGroup) throws Exception {
        resourceGroup = ResourceGroup;
        vm = virtualMachineObject.getAllVirtualMachine(resourceGroup);
    }

    @Then("^I validate that \"([^\"]*)\" Virtual Machine exists$")
    public void i_validate_that_Virtual_Machine_exists(String virtualmachine) throws Exception {
        boolean isVMPresent = false;
        for (int i = 0; i < vm.size(); i++) {
            System.out.println("Virtual Machine - " + vm.get(i).name());
            if (vm.get(i).name().equals(virtualmachine)) {
                isVMPresent = true;
            }
        }
        Assert.assertEquals("Virtual Machine " + vm + " is not present.", true, isVMPresent);
    }

    @Then("^I validate the properties of \"([^\"]*)\" Virtual Machine$")
    public void i_validate_the_properties_of_Virtual_Machine(String virtualMachineName, Map<String, String> properties) throws Exception {
        Iterator<String> iterator2 = properties.keySet().iterator();
        while (iterator2.hasNext()) {
            String property = iterator2.next();
            switch (property) {
                case "Tags":
                    break;
                case "Operating system":
                    String os = virtualMachineObject.getOperationSystem(resourceGroup, virtualMachineName);
                    System.out.println("OS Name - " + os);
                    Assert.assertEquals("Operating System doesn't Match", properties.get(property), os);
                    break;
                case "Public IP address":
                    String publicIP = virtualMachineObject.getPublicIP(resourceGroup, virtualMachineName);
                    System.out.println("Public IP Address - " + publicIP);
                    Assert.assertEquals("Public IP address has been changed", properties.get(property), publicIP);
                    break;
                case "Private IP address":
                    String privateIP = virtualMachineObject.getPrivateIP(resourceGroup, virtualMachineName);
                    System.out.println("Private IP Address - " + privateIP);
                    Assert.assertEquals("Private IP address has been changed", properties.get(property), privateIP);
                    break;
                case "Size":
                    String size = virtualMachineObject.getSize(resourceGroup, virtualMachineName);
                    System.out.println("OS Size - " + size);
                    Assert.assertEquals("Size of VM has has been changed", properties.get(property), size);
                    break;
            }

        }
    }

}
