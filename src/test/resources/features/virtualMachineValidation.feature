Feature: Verify the Virtual Machine Configurations

  @VM @InfraConfig
  Scenario: Verify the Configurations of Virtual Machines
    When I capture all VMs in "Test_ResourceGroup-VodQa" resource group
    Then I validate that "Test-VirtualMachine-VodQa" Virtual Machine exists
    And I validate the properties of "Test-VirtualMachine-VodQa" Virtual Machine
      | Operating system       | Linux (ubuntu 18.04)                  |
      | Public IP address      | 20.193.230.149                        |
      | Private IP address     | 10.0.0.4                              |
      | Size                   | Standard_B1ls                         |
      | Tags                   |                                       |