package com.donatus.activity_tracker_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivityTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityTrackerApiApplication.class, args);
	}

//	@Bean
//	@Autowired
//	public CommandLineRunner commandLineRunner(ClientServices services){
//		return runner -> {
//			register(services);
//		};
//	}
//
//	public void register(ClientServices services){
//
//		ClientRegistrationDTO client1 = new ClientRegistrationDTO();
//
//		client1.setFirstName("Donatus");
//		client1.setLastName("Okpala");
//		client1.setEmail("electroodob@gamil.com");
//		client1.setPassword("1234");
//		client1.setConfirmPassword("1234");
//		client1.setOccupation("Robotics Engineer");
//		client1.setAddress("Anambra");
//
//		services.registerClient(client1);
//
//		ClientRegistrationDTO client2 = new ClientRegistrationDTO();
//
//		client1.setFirstName("Mike");
//		client1.setLastName("John");
//		client1.setEmail("mike@gamil.com");
//		client1.setPassword("1234");
//		client1.setConfirmPassword("1234");
//		client1.setOccupation("Software Engineer");
//		client1.setAddress("Decagon");
//
//		services.registerClient(client2);
//
//	}


}
