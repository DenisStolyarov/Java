package bs;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

public class Client_First implements MessageListener {
    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
    JMSConsumer consumer;
    String selector = "symbol = 'BSTU'";

    Client_First() {
        try (JMSContext context = factory.createContext("admin", "admin", JMSContext.AUTO_ACKNOWLEDGE)) {
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination topic = context.createTopic("CommonTopic");

            consumer = context.createConsumer(topic, selector);

            consumer.setMessageListener(this);
            System.out.println("Client_First 123 Listening to CommonTopic...");
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
        new Client_First();
    }
}
