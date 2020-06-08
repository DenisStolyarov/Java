package bs;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

public class Sender {
    public static void main(String[] args) {

        ConnectionFactory factory;

        factory = new com.sun.messaging.ConnectionFactory();

        try (JMSContext context = factory.createContext("admin", "admin", JMSContext.SESSION_TRANSACTED)) {

            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination topic = context.createTopic("CommonTopic");
            /////////////////////////////////

            TextMessage sendMsg = context.createTextMessage();
            sendMsg.setStringProperty("symbol", "BSTU");
            sendMsg.setText("Hello world!");

            TextMessage sendMsg2 = context.createTextMessage();
            sendMsg2.setText("Hello everybody!");
            // Send msg
            context.createProducer()
                    .setPriority(8)
                    .setTimeToLive(1000)
                    .setDeliveryMode(DeliveryMode.NON_PERSISTENT)
                    .send(topic, sendMsg);

            context.createProducer()
                    .setPriority(1)
                    .setTimeToLive(500)
                    .setDeliveryMode(DeliveryMode.PERSISTENT)
                    .send(topic, sendMsg2);

            context.commit();

            System.out.println("Placed an order to CommonTopic");

            /////////////////////////////////

        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
