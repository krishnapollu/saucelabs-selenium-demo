package com.testautomation.commons;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class DriverSetup {
    
    protected RemoteWebDriver driver;

    @BeforeTest
    @Parameters({"appURL", "SLusername", "SLaccessKey"})
    public void setup(String app_url, String sl_username, String sl_accessKey){

        try{
            TestParameters.sl_username = System.getProperty("SLusername");
            TestParameters.sl_accessKey = System.getProperty("SLaccessKey");
        }catch(Exception e){
            TestParameters.sl_username = sl_username;
            TestParameters.sl_accessKey = sl_accessKey;
        }

        System.out.println("Creds (username): "+TestParameters.sl_username);
        System.out.println("Creds (key): "+TestParameters.sl_accessKey);

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", TestParameters.sl_username);
        sauceOptions.put("accessKey", TestParameters.sl_accessKey);
        sauceOptions.put("build", "selenium-build-"+System.currentTimeMillis());
        sauceOptions.put("name", "SauceLabsTest");
        browserOptions.setCapability("sauce:options", sauceOptions);
        
        try{
            URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
            driver = new RemoteWebDriver(url, browserOptions);
            driver.get(app_url);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
