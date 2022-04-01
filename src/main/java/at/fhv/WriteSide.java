package at.fhv;

import eventside.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import writeside.EventPublisher;
import writeside.domain.Room;
import writeside.domain.RoomRepository;

@SpringBootApplication
@Configuration
@ComponentScan("writeside")
public class WriteSide {

    @Autowired
    private EventPublisher publisher;

    @Autowired
    private RoomRepository roomRepository;

    public static void main(String[] args) {
        SpringApplication.run(WriteSide.class, args);

    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Event event = new Event();
            event.setContent("This is the content!");
            event.setCustomer("Customer1");
            event.setTimestamp(System.currentTimeMillis());
            System.out.println("Result: " + publisher.publishEvent(event));

            roomRepository.addRoom(new Room("102",2));
        };
    }
}
