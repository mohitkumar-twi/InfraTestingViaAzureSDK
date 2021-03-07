Feature: Verify the storage account configurations

  @storageaccount @infraTesting @prod
  Scenario Outline: verify the existence of storage account
    When I go to the azure "" resource group
    Then I validate that "<StorageAccount>" account exist
    Examples:
      | StorageAccount |
      |                |
      |                |


  @storageaccount @infraTesting @prod
  Scenario Outline: verify the existence of blob in the container
    When I open the "<StorageAccount>" account
    Then I validate that "<Folders>" container exist
    Examples:
      | Folders | StorageAccount |
      |         |                |




