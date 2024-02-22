package org.delivery.storeadmin.domain.userorder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.common.message.model.UserOrderMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserOrderConsumer {

    @RabbitListener(queues = "delivery.queue")
    public void userOrderConsumer(
            UserOrderMessage userOrderMessage
    ) {
        log.info("Received message queue: {}", userOrderMessage);
    }
}
