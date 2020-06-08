import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

public class DirectMessageReceiver implements MessageListener {
    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
    JMSConsumer consumer;

    DirectMessageReceiver() {
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination cardsQueue = context.createQueue("CommonQueue");
            consumer = context.createConsumer(cardsQueue);
            consumer.setMessageListener(this);
            System.out.println("Listening to theBankCardQueue...");
            //  wait for messages
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void onMessage(Message msg) {
        try {
            System.out.println("Got the text message from the CommonQueue: " + msg.getBody(String.class));
            System.out.println("\n = Here's what toString() on the message prints \n" + msg);
        } catch (JMSException e) {
            System.err.println("JMSException: " + e.toString());
        }
    }

    public static void main(String[] args) {
        new DirectMessageReceiver();
    }


}
