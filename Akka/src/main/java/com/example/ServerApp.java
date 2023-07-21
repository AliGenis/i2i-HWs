package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.Scanner;

// Define the server actor
class Server extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, message -> {
                    System.out.println("Server: Received message from client" + message);
                    getSender().tell("Hello from Actor 2!", getSelf()); // Respond to the client
                })
                .build();
    }
}

public class ServerApp {
    public static void main(String[] args) {
        Config config = ConfigFactory.load("server.conf");

        ActorSystem system = ActorSystem.create("ServerSystem", config);
        ActorRef server = system.actorOf(Props.create(Server.class), "server");

        System.out.println("Server: Ready to receive messages.");
        System.out.println("You can write 0 to quit anytime");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        while (!input.equals("0")){
            input = scan.next();
        }
        system.terminate();
    }
}
