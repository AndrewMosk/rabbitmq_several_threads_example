import com.rabbitmq.client.ConnectionFactory;

public class ConnFactory extends ConnectionFactory {
    public ConnFactory() {
        this.setUsername("guest");
        this.setPassword("guest");
        this.setVirtualHost("/");
        this.setHost("127.0.0.1");
        this.setPort(5672);
    }
}
