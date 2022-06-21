package id.co.nds.catalogue.schedulers;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FixedDelayScheduler {
    static final Logger logger = LogManager.getLogger(FixedRateScheduler.class);
    Integer counterB = 0;

    //@Scheduled(fixedDelay = 10000)
    public void fixedDelayScheduler() throws Exception{
        Integer counterA =0;
        logger.debug("Start FixedRateScheduller at " + Calendar.getInstance().getTime());
        logger.info("Counter-A: " + counterA);
        logger.info("Counter-A: " + counterA);
        counterA++;
        counterB++;

        Thread.sleep( 5000);
    }

    //@Scheduled(fixedDelayString = "${param.scheduler.fixeddelay.ms}")
    public void fixedDelaycheduler() throws Exception{
        Integer counterA =0;
        logger.debug("Start FixedRateScheduller at " + Calendar.getInstance().getTime());
        logger.info("Counter-A: " + counterA);
        logger.info("Counter-A: " + counterA);
        counterA++;
        counterB++;

        Thread.sleep( 5000);
    }
}
