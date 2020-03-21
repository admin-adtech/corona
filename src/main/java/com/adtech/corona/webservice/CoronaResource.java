package com.adtech.corona.webservice;

import com.adtech.corona.model.CoronaData;
import com.adtech.corona.service.CoronaDataService;
import com.adtech.corona.service.CronJobRunner;
import com.adtech.corona.service.SeleniumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("corona")
public class CoronaResource {

    @Autowired
    private CoronaDataService coronaDataService;

    @Autowired
    private SeleniumService seleniumService;

    @Autowired
    private CronJobRunner cronJobRunner;


    @RequestMapping("/now")
    public CoronaData nowCorona() {
        return seleniumService.run(true);
    }

    @RequestMapping("/now/show")
    public CoronaData nowShowCorona() {
        return seleniumService.run(false);
    }

    @RequestMapping("/latest")
    public CoronaData latestCorona() {
        return coronaDataService.findLatest();
    }

    @RequestMapping("/sync")
    public String syncCorona() {
        seleniumService.sync();
        return "Data Sync Started ...";
    }

    @RequestMapping("/status")
    public CoronaData statusCorona() {
        return seleniumService.run(true);
    }

    @RequestMapping("/start/ulloop")
    public String startCron() {
        cronJobRunner.ulLoop();
        return "Unlimited Loop Started...";
    }


}
