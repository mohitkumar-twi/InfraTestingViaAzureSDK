Feature: Verify the keyvaults

  @keyvault @infraTesting @prod
  Scenario: verify the existence of keyvault
    When i capture all the keyvault from the "" resource group
    Then i validate if the "" keyvault  is present