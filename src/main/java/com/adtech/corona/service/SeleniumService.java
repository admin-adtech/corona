package com.adtech.corona.service;

import com.adtech.corona.model.CoronaData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SeleniumService {

    private static String driverP = "C:\\My_Projects\\utils\\chromedriver.exe";
    static {
        String os = System.getProperty("os.name");
        if (os.contains("Windows")){
            driverP = "C:\\My_Projects\\utils\\chromedriver.exe";
        }else {
            driverP="/usr/local/bin/chromedriver";
        }
        System.setProperty("webdriver.chrome.driver", driverP);
    }

    @Autowired
    private CoronaDataService coronaDataService;

    public CoronaData run(Boolean isHeadless) {

        String coronaBaseUrl = "https://www.worldometers.info/coronavirus/";
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--silent");
        if (isHeadless) {
            options.addArguments("--incognito");
            options.addArguments("--headless");
        }
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        driver.get(coronaBaseUrl);
        try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }

        int totalCase = 0;
        int death = 0;
        int recovered = 0;

        try {
            List<WebElement> webElements = driver.findElements(By.xpath("//div[@class=\"content-inner\"]//div[@id=\"maincounter-wrap\"]"));
            for (int i = 1; i <= webElements.size(); i++) {
                String key = driver.findElement(By.xpath("//div[@class=\"content-inner\"]//div[@id=\"maincounter-wrap\"][" + i + "]/h1")).getText();
                String value = driver.findElement(By.xpath("//div[@class=\"content-inner\"]//div[@id=\"maincounter-wrap\"][" + i + "]/div")).getText();

                if (key.toLowerCase().contains("coronavirus")) {
                    totalCase = Integer.parseInt(value.replace(",", "").trim());
                } else if (key.toLowerCase().contains("death")) {
                    death = Integer.parseInt(value.replace(",", "").trim());
                } else if (key.toLowerCase().contains("recovered")) {
                    recovered = Integer.parseInt(value.replace(",", "").trim());
                } else {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!--------Error Key Found------------!!!!!!!!!!!!!!!!!! : " + key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.close();

        CoronaData coronaData = new CoronaData();
        coronaData.setTime(new Date().toString());
        coronaData.setInfected(totalCase == 0 ? null : totalCase);
        coronaData.setDeaths(death == 0 ? null : death);
        coronaData.setRecovered(recovered == 0 ? null : recovered);
        CoronaData coronaData1 = coronaDataService.save(coronaData);

        System.out.println("!!!!!!!!!!!!!!!!!----Successfully Data Saved----!!!!!!!!!!!!!!");

        return coronaData1;
    }

    @Async
    public void sync() {
        run(true);
    }
}
