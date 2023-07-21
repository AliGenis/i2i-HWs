package com.example;

import akka.actor.*;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientApp {
    public static void main(String[] args){
        Config config = ConfigFactory.load("client.conf");

        ActorSystem system = ActorSystem.create("ClientSystem", config);
        ActorRef clientActor = system.actorOf(Props.create(Client.class), "client");

        // Send a "Hello" message to the server
        clientActor.tell("Hi from actor 1!", ActorRef.noSender());

        System.out.println("You can write 0 to quit anytime");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        while (!input.equals("0")){
            input = scan.next();
        }
        system.terminate();
    }
}

// Define the client actor
class Client extends AbstractActor {

    private final ActorSelection serverActor;
    private final int disconnectWaitTime;
    public Client() {
        ActorSystem system = getContext().getSystem();
        this.serverActor = system.actorSelection("akka.tcp://ServerSystem@localhost:2553/user/server");
        this.disconnectWaitTime = 5;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, response -> {
                    Timeout timeout = new Timeout(Duration.create(disconnectWaitTime,TimeUnit.SECONDS));
                    Future<Object> future = (Future<Object>) Patterns.ask(serverActor, response, timeout);
                    String result = (String) Await.result(future, timeout.duration());
                    System.out.println("Received response: " + result);
                })
                .build();
    }
}
