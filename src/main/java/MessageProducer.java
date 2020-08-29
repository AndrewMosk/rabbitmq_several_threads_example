import com.rabbitmq.client.*;

public class MessageProducer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnFactory();

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        String exchangeName = "myExchange";
        String routingKey = "testRoute";

        for (int i=1; i<21; i++) {
            byte[] messageBodyBytes = ("test "+i).getBytes();
            channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);
        }

//        byte[] messageBodyBytes = ("test ").getBytes();
//        channel.basicPublish(exchangeName, routingKey,
//                new AMQP.BasicProperties.Builder().contentType("text/plain").deliveryMode(2).priority(1).userId("guest").build(),
//                messageBodyBytes);
        channel.close();
        conn.close();
    }
}