package com.camelcase.cron;

import com.camelcase.service.DataStreamCaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CronService {

    @Autowired
    private DataStreamCaptureService dataStreamCaptureService;

    @Value("${event.duration}")
    private Long duration;

    @Value("${event.cron.enable}")
    private boolean enableEventCron;

    @Scheduled(cron = "${event.cron.schedule}")
    public void sendSmsToExpiredYogPrakashSubscribers() {
        if(enableEventCron){
            Long from = System.currentTimeMillis();
            Long to = duration * 60000;
            while(to < from){
                dataStreamCaptureService.sendDataToKafkaTopic();
            }
        }

    }

}
