package com.evison.csv;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {

// topic configuration
	@Bean
	public NewTopic topicCreator1() {
		return TopicBuilder.name("evisionTopic1").build();
	}

	@Bean
	public NewTopic topicCreator2() {
		return TopicBuilder.name("evisionTopic2").build();
	}

	// producer cofiguration
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;

	public Map<String, Object> producerConfig() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return props;
	}

	@Bean
	public ProducerFactory<String, csvModel> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}

	// send message way
	@Bean
	public KafkaTemplate<String, csvModel> kafkaTemplate(ProducerFactory<String, csvModel> producerFactory) {
		return new KafkaTemplate<>(producerFactory);
	}

	// kafka consumer config

	public Map<String, Object> consumerConfig() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return props;
	}

	@Bean
	public ConsumerFactory<String, csvModel> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfig());
	}
	// listener liston for all message from all topics

	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, csvModel>> listenerFactory(
			ConsumerFactory<String, csvModel> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, csvModel> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}
}
