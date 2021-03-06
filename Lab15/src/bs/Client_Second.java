package bs;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

public class Client_Second implements MessageListener {
    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
    JMSConsumer consumer;

    Client_Second() {
        try (JMSContext context = factory.createContext("admin", "admin", JMSContext.CLIENT_ACKNOWLEDGE)) {

            context.setClientID("Client14");
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination topic = context.createTopic("CommonTopic");

            consumer = context.createDurableConsumer((Topic)topic, "SecurityCenter");

            consumer.setMessageListener(this);
            System.out.println("Client_Second Listening to CommonTopic...");
            //  wait for messages
            Thread.sleep(150000);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void onMessage(Message msg) {
        try {
            System.out.println("Got the text message from the CommonTopic: " + msg.getBody(String.class));
            //System.out.println("\n Here's what toString() on the message prints \n" + msg);
        } catch (JMSException e) {
            System.err.println("JMSException: " + e.toString());
        }
    }

    public static void main(String[] args) {
        new Client_Second();
    }
}