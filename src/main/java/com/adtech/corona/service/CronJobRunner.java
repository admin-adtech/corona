package com.adtech.corona.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronJobRunner {

    @Autowired
    private SeleniumService seleniumService;

    @Scheduled(cron = "0 * * * * ?")
    public void runCron() {
        System.out.println("!!!!!!!!!!!!!!!!!@@@@@@@@@@@@----CronJob Started----@@@@@@@@@@@@!!!!!!!!!!!!!!");
        seleniumService.sync();
    }

    @Async
    public void ulLoop() {
        int a = 0;
        while (0 == a) {
            seleniumService.sync();
        }
    }
}
