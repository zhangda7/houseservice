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
@Component
public class LogReceiver {

    private static Logger logger = LoggerFactory.getLogger(LogReceiver.class);

    public static Props props () {
        return Props.create(LogActor.class, () -> new LogActor());
    }

    private ActorRef selfActor;

    public ActorRef getActor() {
        return this.selfActor;
    }

    @PostConstruct
    private void init() {
        final ActorSystem system = ActorSystem.create("logAkka");
        selfActor = system.actorOf(LogReceiver.props(), "logActor");
    }

}
