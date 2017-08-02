package com.spare.house.controller;

import akka.actor.ActorRef;
import com.spare.house.log.LogReceiver;
import com.spare.house.model.LogMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dada on 2017/7/16.
 */
@RestController
public class HelloController {

    @Autowired
    LogReceiver logReceiver;

    @RequestMapping("/hello")
    public String hello() {
        logReceiver.getActor().tell(new LogMsg(200, "Success"), ActorRef.noSender());
        return "Hello world";
    }

}
