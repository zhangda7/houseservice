package com.spare.house.dao.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by dada on 2017/8/13.
 */
public class ActorHierarchyExperiments {

        public static void main(String[] args) throws java.io.IOException {
            ActorSystem system = ActorSystem.create("test");

            ActorRef firstRef = system.actorOf(Props.create(PrintMyActorRefActor.class), "first-actor");
            System.out.println("First: " + firstRef);
            firstRef.tell("printit", ActorRef.noSender());

            System.out.println(">>> Press ENTER to exit <<<");
            try {
                System.in.read();
            } finally {
                system.terminate();
            }
        }

}
