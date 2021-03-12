Feature: Verify the storage account configurations

  @storageaccount @InfraConfig
  Scenario: Verify the storage account
    When I capture all StorageAccount in "Test_ResourceGroup-VodQa" resource group
    Then I validate that "teststorageaccvodqa" account exist
    And I validate that below containers are present in the "teststorageaccvodqa" storage Account
      | testconatinervodqa1 |
      | testcontainervodqa  |





