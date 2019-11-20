package com.cci.payments.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumSet;

@Service
@Profile({"prod"}) //TODO change on prod
public class SchedulingService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulingService.class);

    @Autowired
    private PaymentService paymentService;

    @Scheduled(fixedDelay = 10_000) //Lunch each 10 seconds
//    @Scheduled(cron = "0 49,50 15 * * *") //Lunch each day at 15-49 and 15-50
    public void scheduleFixedRateTask() {
        logger.info("<--- Start Process POSTPONED payments");
        int count = paymentService.processPostponedPayment();
        logger.info("<--- End Process POSTPONED payments. Total processed = " + count);
    }



}
