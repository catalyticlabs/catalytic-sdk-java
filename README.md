[![](https://jitpack.io/v/catalyticlabs/catalytic-sdk-java.svg)](https://jitpack.io/#catalyticlabs/catalytic-sdk-java)
![CI](https://github.com/catalyticlabs/catalytic-sdk-java/workflows/CI/badge.svg)
[![codecov](https://codecov.io/gh/catalyticlabs/catalytic-sdk-java/branch/master/graph/badge.svg)](https://codecov.io/gh/catalyticlabs/catalytic-sdk-java)

# catalytic/sdk
> Catalytic's Java SDK

The Catalytic SDK allows you to integration your app with the Catalytic Platform.

Key Benefits:

 - Easily add powerful capabilities to your app like customizable ETL, webforms, notifications and hundreds of integrations
 - Dramatically lower the cost of risk of rapidly prototyping new functionality for your customers
 - Allow business users to build and change the business logic without changing your app
 - Move cumbersome and complex processes out of your app and into Catalytic where they are easy to manage
 - Easily connect actions users take in your app to backoffice processes in your business.
 - Let Catalytic handle the compliance burden of storing and processing PII and ePHI securely


## Requirements
Java 8 and later

## Installation

### Gradle

Add the [Jitpack](https://jitpack.io/) repository

```groovy
repositories {
    maven {
        url "https://jitpack.io"
    }
}
```

Add the Catalytic SDK dependency

```groovy
compile 'com.github.catalyticlabs:catalytic-sdk-java:0.0.1'
```

### Maven

Add the [Jitpack](https://jitpack.io) repository

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
  <groupId>com.github.catalyticlabs</groupId>
  <artifactId>catalytic-sdk-java</artifactId>
  <version>0.0.1</version>
  <scope>compile</scope>
</dependency>
```

## Documentation
Documentation can be found at [https://catalytic-developer.readme.io/reference/catalytic-java-sdk](https://catalytic-developer.readme.io/reference/catalytic-java-sdk).
