package com.evison.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/csv")
public class CsvKafkaController {
	
	private KafkaTemplate<String, csvModel> kafkaTemplate;

	
	@Autowired
	public CsvKafkaController(KafkaTemplate<String, csvModel> kafkaTemplate) {
		
		this.kafkaTemplate = kafkaTemplate;
	}
	
	@PostMapping
	public void post(@RequestBody csvModel model) {
		kafkaTemplate.send("evisionTopic1",model);
	}

}
