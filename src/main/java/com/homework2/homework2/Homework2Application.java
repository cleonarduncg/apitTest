package com.homework2.homework2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Homework2Application {

	public static void main(String[] args) {
		SpringApplication.run(Homework2Application.class, args);
                weather();
                System.exit(0);
	}
        
        public static void weather() {
        try {
            String url = "https://api.tomorrow.io/v4/weather/forecast?location=charlotte&fields=temperature&timesteps=1h&units=imperial&apikey=riSKVw2ebqUozvyoxbkWDMfDmUJoPDl0";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonPrice = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonPrice);

            //gets coin name
            String cityName = root.findValue("name").asText();
            //gets coin value in USD
            double temperature = root.findValue("temperature").asDouble();
            //print vals
            System.out.println("City: " + cityName);
            System.out.println("Temperature: " + temperature);

        } catch (JsonProcessingException ex) {
            System.out.println("error in Tomorrow API");
        }
    }

}
