package com.evison.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

@Component
public class KafkaListeners {

	@KafkaListener(topics = "evisionTopic1", groupId = "groupId")
	void listenerStoreData1(String data) {
		try {
			PrintWriter fileout = new PrintWriter(new FileWriter("C://Users//lessThan1000.txt"));
			
			
			JSONPObject json=new JSONPObject(data,csvModel.class);
			System.out.println("fffffffffff");
			
			csvModel model = new ObjectMapper().readValue(data, csvModel.class);
			int amountToStore = calculateFeesAmount(model.getAmount());
			storeAmount(amountToStore, fileout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("receivedddddd" + data);
	}

	@KafkaListener(topics = "evisionTopic2", groupId = "groupId")
	void listenerStoreData2(String data) {
		try {
			PrintWriter fileout = new PrintWriter(new FileWriter("C://Users//moreThan1000.txt"));
			csvModel model = new ObjectMapper().readValue(data, csvModel.class);
			int amountToStore = calculateFeesAmount(model.getAmount(), 20);
			storeAmount(amountToStore, fileout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int calculateFeesAmount(int amount) {
		return amount - (10 * amount / 100);
	}

	public static int calculateFeesAmount(int amount, int dollarFees) {
		int AmountInDollar = amount / dollarFees;
		return AmountInDollar - (20 * AmountInDollar / 100);
	}

	public void storeAmount(int amount, PrintWriter fileout) {
		try {
			fileout.println(amount);
			fileout.close();
			System.out.println("success...");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
