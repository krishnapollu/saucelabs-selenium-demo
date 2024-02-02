# saucelabs-selenium-demo
Sample project to demonstrate the use of SauceLabs platform to run automated tests in remote browser sessions.
This project / test suite uses Selenium + TestNG

## Configurations

No additional dependencies specific to SauceLabs need to be added in pom.xml

### Driver Setup
Capabilities / BrowserOptions need to be added inorder to run the tests in LambdaTest Browsers. Use [SauceLabs Platform Configurator](https://saucelabs.com/products/platform-configurator) to generate the same for your required platform / browsers versions

- Sample code for Chrome latest (I am sending the credentials as Parameters and storing them in ``TestParameters.sl_username``, ``TestParameters.sl_accessKey``. U may do the same or hardcode it in the below code.)
```Java
         ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", TestParameters.sl_username);
        sauceOptions.put("accessKey", TestParameters.sl_accessKey);
        sauceOptions.put("build", "selenium-build-"+System.currentTimeMillis());
        sauceOptions.put("name", "SauceLabsTest");
        browserOptions.setCapability("sauce:options", sauceOptions);
```

- Initialize the driver
```Java
        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        driver = new RemoteWebDriver(url, browserOptions);
```

### Passing the Credentials
- If you would like to pass your SauceLabs credentials during runtime rather than hardcoding it, you can follow the below options:

- As TestNG parameters
```xml
    <parameter name="SLusername" value="YOUR_USERNAME" />
    <parameter name="SLaccessKey" value="YOUR_ACCESSKEY" />
```

- As System Properties
```xml
    <plugin>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.22.1</version>
        <configuration>
            <suiteXmlFiles>
            <suiteXmlFile>testng.xml</suiteXmlFile>
            </suiteXmlFiles>
            <systemProperties>
                <property>
                    <name>SLusername</name>
                    <value>${SLusername}</value>
                </property>
                <property>
                    <name>SLaccessKey</name>
                    <value>${SLaccessKey}</value>
                </property>
            </systemProperties>
        </configuration>
    </plugin>
```
- In this case, you will only be able to trigger the test from CLI

```bash
    mvn clean test -DSLusername=YOUR_USERNAME -DSLaccessKey=YOUR_ACCESSKEY
```

## SauceLabs Web UI
You will be able to view the Test Build progress and results from the SauceLabs Dashboard. 

- Log in to your SauceLabs account
- Navigate to Automated > Test Results.

And you will be able to view the In Progress as well as Completed tests and their details including step wise logs and execution video.

![Test Builds](https://github.com/krishnapollu/saucelabs-selenium-demo/blob/main/images/sl-automation-results.png)

Click on one of the builds and you will be able to see a detailed view of the test execution

![Test Details](https://github.com/krishnapollu/saucelabs-selenium-demo/blob/main/images/sl-automation-test-details.png)

## Detailed Documentation
- [SauceLabs Docs](https://docs.saucelabs.com/overview/)
