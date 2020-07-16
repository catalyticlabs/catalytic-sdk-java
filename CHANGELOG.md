# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

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

[Unreleased]: https://github.com/catalyticlabs/catalytic-sdk-java/compare/0.1.0...HEAD
[0.1.0]: https://github.com/catalyticlabs/catalytic-sdk-java/compare/0.1.0...0.0.2
[0.0.2]: https://github.com/catalyticlabs/catalytic-sdk-java/compare/0.0.2...0.0.1
[0.0.1]: https://github.com/catalyticlabs/catalytic-sdk-java/releases/tag/0.0.1