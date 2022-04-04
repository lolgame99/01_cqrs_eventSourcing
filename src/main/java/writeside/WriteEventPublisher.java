package writeside;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import eventside.domain.Event;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WriteEventPublisher {

    private final WebClient localApiClient = WebClient.create("http://localhost:8080");

    public WriteEventPublisher() {
    }

    public Boolean publishBookRoomEvent(BookingCreatedEvent event) {
        System.out.println("[WRITE] Publishing BookRoomEvent");
        return localApiClient
                .post()
                .uri("/createBooking/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event),Event.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public Boolean publishCancelBookingEvent(BookingCancelledEvent event) {
        System.out.println("[WRITE] Publishing CancelBookingEvent");
        return localApiClient
                .post()
                .uri("/cancelBooking/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event),Event.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
