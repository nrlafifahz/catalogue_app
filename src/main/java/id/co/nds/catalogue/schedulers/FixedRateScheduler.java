package id.co.nds.catalogue.schedulers;

import java.util.Calendar;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FixedRateScheduler {
    static final Logger logger = LogManager.getLogger(FixedRateScheduler.class);
    Integer counterB = 0;

    //@Scheduled(fixedRate = 10000)
    // public void fixedRateScheduler() throws Exception{
    //     Integer counterA =0;
    //     logger.debug("Start FixedRateScheduller at " + Calendar.getInstance().getTime());
    //     logger.info("Counter-A: " + counterA);
    //     logger.info("Counter-A: " + counterA);
    //     counterA++;
    //     counterB++;
    // }

    // //@Scheduled(fixedRateString =  "${param.scheduler.fixedrate.ms}")
    // public void fixedRateScheduler() throws Exception{
    //     Integer counterA =0;
    //     logger.debug("Start FixedRateScheduller at " + Calendar.getInstance().getTime());
    //     logger.info("Counter-A: " + counterA);
    //     logger.info("Counter-A: " + counterA);
    //     counterA++;
    //     counterB++;

    //     Thread.sleep(5000);
    // }
    
}
