package eventside.infrastructure;

import eventside.domain.BookingCreatedEvent;
import eventside.domain.Event;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class Subscriber {
    private WebClient client;

    public Subscriber(String host){
        this.client = WebClient.create(host);
    }

    public void notify(Event event){
        String url = null;
        if (event instanceof BookingCreatedEvent){
            url = "/createBooking";
        }else{
            url = "/cancelBooking";
        }
        client.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event), Event.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }


}
