package com.spare.house.log;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.spare.house.model.LogMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by dada on 2017/8/1.
 */
public class LogActor extends AbstractActor {

    private static Logger logger = LoggerFactory.getLogger(LogActor.class);

    public static Props props () {
        return Props.create(LogActor.class, () -> new LogActor());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(LogMsg.class, log -> {
            logger.info("Receive log msg {} {} {}", log.getCode(), log.getMsg(), log.getTimestamp());
            //need insert to log db
        }).build();
    }
}
