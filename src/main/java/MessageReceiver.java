import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class MessageReceiver {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnFactory();

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        String exchangeName = "myExchange";
        String queueName = "myQueue";
        String routingKey = "testRoute";
        boolean durable = true;

        channel.exchangeDeclare(exchangeName, "direct", durable);
        channel.queueDeclare(queueName, durable, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, false, consumer);
        int i = 0;




        while (true) {
            QueueingConsumer.Delivery delivery;
            try {
                delivery = consumer.nextDelivery();
                new MessageThread(channel, new String(delivery.getBody()), delivery.getEnvelope().getDeliveryTag()).start();
                i++;
                System.out.println("thread " + i + " started");
            } catch (InterruptedException ignored) {
            }
        }
//        channel.close();
//        conn.close();
//        System.out.println("EXIT");
    }
}