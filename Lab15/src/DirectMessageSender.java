import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;
import java.io.Serializable;

public class DirectMessageSender {
    public static void main(String[] args) {

        ConnectionFactory factory;

        factory = new com.sun.messaging.ConnectionFactory();

        try (JMSContext context = factory.createContext()) {

            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination ordersQueue = context.createQueue("CommonQueue");
            JMSProducer producer = context.createProducer();
            ////////////////////////////////////////////

//            TextMessage sendMsg = context.createTextMessage();
//            sendMsg.setText("Hello world!");
//            // Send msg
//            producer.send(ordersQueue, sendMsg);
//
//            System.out.println("Placed an order to CommonQueue");
//
//            // Get message synchronise
//            TextMessage message = (TextMessage) context.createConsumer(ordersQueue).receive();
//            System.out.println(message.getText());

            /////////////////////////////////
            MyClass myClass = new MyClass();
            myClass.count = 10;
            myClass.text = "Hello world!";

            ObjectMessage sendObjMsg = context.createObjectMessage(myClass);

            // Send msg
            producer.send(ordersQueue, sendObjMsg);

            System.out.println("Placed an order to CommonQueue");
            // Get message synchronise
            ObjectMessage getObjMsg = (ObjectMessage) context.createConsumer(ordersQueue).receive();
            MyClass getMyClass = getObjMsg.getBody(MyClass.class);
            System.out.println(getMyClass.count + " " + getMyClass.text);

        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static class MyClass implements Serializable {
        public int count = 0;
        public String text = "";
    }
}
