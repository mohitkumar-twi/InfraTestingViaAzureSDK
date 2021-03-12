Feature: Verify the keyvaults

  @keyvault @InfraConfig
  Scenario: verify the Configurations of Keyvault
    When I capture all the keyvaults from the "Test_ResourceGroup-VodQa" resource group
    Then I validate if the "Test-KeyVault-VodQa" keyvault  is present
    And I validate below Properties of "Test-KeyVault-VodQa" keyvault
      | Property         | Value    |
      | Tags             |          |
      | Soft-delete      | Enabled  |
      | Purge protection | Disabled |