package talgat.demo.store.back.services.email;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import talgat.demo.store.back.models.OrderCompleteDTO;

@Service
public class OrderEmailService {
    private RabbitTemplate rabbit;


    public OrderEmailService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public void sendOrderEmail(OrderCompleteDTO orderCompleteDTO){
        MessageConverter converter = rabbit.getMessageConverter();
        MessageProperties properties = new MessageProperties();
        Message message = converter.toMessage(orderCompleteDTO, properties);
        rabbit.send("talgat-demo-store", "order-email", message);
    }
}
