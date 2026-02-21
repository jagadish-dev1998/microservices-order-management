package com.ordermanagement.orderservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ordermanagement.orderservice.event.OrderEvent;

@Service
public class OrderProducer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	private static final String TOPIC = "order-topic";

	public void sendOrderEvent(OrderEvent event) {
		kafkaTemplate.send(TOPIC, event);
	}
}
