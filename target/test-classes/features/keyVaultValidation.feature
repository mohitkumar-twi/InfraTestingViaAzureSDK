Feature: Verify the keyvaults

  @keyvault
  Scenario: verify the existence of keyvault
    When i capture all the keyvault from the "Test_ResourceGroup-VodQa" resource group
    Then i validate if the "Test-KeyVault-VodQa" keyvault  is present