package id.co.nds.catalogue.schedulers;

import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import id.co.nds.catalogue.entities.ProductEntity;
import id.co.nds.catalogue.services.ProductServices;

@Component
public class CronScheduler {
    static final Logger logger = LogManager.getLogger(FixedRateScheduler.class);

    @Autowired
    ProductServices productServices;

    Integer counterB = 0;

   @Scheduled(cron = "0 0/3 * * * ?")
    public void cronScheduler() throws Exception{
        Integer counterA =0;
        logger.debug("Start FixedRateScheduller at " + Calendar.getInstance().getTime());
        logger.info("Counter-A: " + counterA);
        logger.info("Counter-A: " + counterA);
        counterA++;
        counterB++;

        List<ProductEntity>products = productServices.findProductsLessThanQuantity();
        for (int i =0; i<products.size(); i++){
            logger.info("product-"+ counterB +": "+ products.get(i).getName());
        }

    }

   // @Scheduled(cron = "${param.scheduler.cron}")
    public void cronParamScheduler() throws Exception{
        Integer counterA =0;
        logger.debug("Start FixedRateScheduller at " + Calendar.getInstance().getTime());
        logger.info("Counter-A: " + counterA);
        logger.info("Counter-A: " + counterA);
        counterA++;
        counterB++;

        Thread.sleep( 15000);
    }
    
}
