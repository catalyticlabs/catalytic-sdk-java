# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- teamName property to User entity
- isTeamAdmin property to User entity
- isDeactivated property to User entity
- isLockedOut property to User entity
- createdDate property to User entity  
- createdDate property to Workflow entity
- rootInstanceId property to Instance entity
- isTest property to Instance entity
- Search Users method
- List Users method
- Search Workflows method
- List Workflows method
- Search Instances method
- List Instances method

### Removed
- Find Users method (Breaking change)
- Find Access Tokens method (Breaking change)
- Instance.getSteps method (Breaking change)
- Instance.setSteps method (Breaking change)
- Find Workflows method (Breaking change)
- Find Instances method (Breaking change)
- Find Instance Steps method (Breaking change)
- Find Data Tables method (Breaking change)
- Find Integrations method (Breaking change)
- Search InstanceSteps method
- completedByEmail property to InstanceStep entity


### Changed
- DataTableColumn.type to DataTableColumn.fieldType (Breaking change)
- Workflow.getPublished to Workflow.getIsPublished (Breaking change)
- Workflow.setPublished to Workflow.setIsPublished (Breaking change)
- Workflow.getArchived to Workflow.getIsArchived (Breaking change)
- Workflow.setArchived to Workflow.setIsArchived (Breaking change)
- Workflow.createdBy to Workflow.createdByEmail (Breaking change)
- Workflow.standardUsers to Workflow.standardUserEmails (Breaking change)
- Workflow.adminUsers to Workflow.adminUserEmails (Breaking change)
- Instance.status from String to InstanceStatus (Breaking change)
- Instance.createdBy to Instance.createdByEmail (Breaking change)
- Instance.visibility from org.catalytic.sdk.generated.model.InstanceVisibilty to InstanceVisibility (Breaking change)
- Instance.fieldVisibility from org.catalytic.sdk.generated.model.FieldVisibility to FieldVisibility (Breaking change)
- Field.restrictions to Field.display (Breaking change)
- DataTableColumn.restrictions to DataTableColumn.display (Breaking change)
- InstanceStep.status from String to InstanceStepStatus (Breaking change)

## [1.0.0] - 10-7-2020
### Added
- Integrations Client
- Get Integration method
- Find Integrations method
- Create Integration method
- Update Integration method
- Get Integration Connection method
- Create Integration Connection method
- Delete Integration Connection method
- getStartDate and getEndDate methods to Instance entity
- getStartDate and getEndDate methods to InstanceStep entity

## [0.1.5]
### Changed
- Updated user agent string

## [0.1.4]
### Fixed
- Setting access token on CatalyticClient not working

## [0.1.3]
### Fixed
- Some exceptions not containing the stacktrace

## [0.1.2]
### Changed
- CatalyticClient.credentials to CatalyticClient.accessTokens

## [0.1.1]
### Fixed
- Error being thrown when trying to read version

## [0.1.0]
### Added
- AccessTokens.waitForApproval method

### Changed
- Allow instantiating CatalyticClient without it finding a token

### Fixed
- Passing in a token directly not working

## [0.0.2]
### Added
- CatalyticLogger and log statements
- AccessTokens.find methods
- AccessTokens.create methods
- AccessTokens.createWithWebApprovalFlow methods
- AccessTokens.getApprovalUrl methods
- AccessTokens.revoke method

### Changed
- Renamed Credentials to AccessTokens
- Environment Variable name to look for the Access Token from CATALYTIC_CREDENTIALS to CATALYTIC_TOKEN
- Access Tokens dir from ~/.catalytic/credentials to ~/.catalytic/tokens

## [0.0.1]
### Added
- Initial methods for Users, Workflows, DataTables, Files, and Instances.

[Unreleased]: https://github.com/catalyticlabs/catalytic-sdk-java/compare/1.0.0...HEAD
[1.0.0]: https://github.com/catalyticlabs/catalytic-sdk-java/compare/0.1.5...1.0.0
[0.1.5]: https://github.com/catalyticlabs/catalytic-sdk-java/compare/0.1.4...0.1.5
[0.1.4]: https://github.com/catalyticlabs/catalytic-sdk-java/compare/0.1.3...0.1.4
[0.1.3]: https://github.com/catalyticlabs/catalytic-sdk-java/compare/0.1.2...0.1.3
[0.1.2]: https://github.com/catalyticlabs/catalytic-sdk-java/compare/0.1.1...0.1.2
[0.1.1]: https://github.com/catalyticlabs/catalytic-sdk-java/compare/0.1.0...0.1.1
[0.1.0]: https://github.com/catalyticlabs/catalytic-sdk-java/compare/0.0.2...0.1.0
[0.0.2]: https://github.com/catalyticlabs/catalytic-sdk-java/compare/0.0.1...0.0.2
[0.0.1]: https://github.com/catalyticlabs/catalytic-sdk-java/releases/tag/0.0.1