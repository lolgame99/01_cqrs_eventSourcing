package eventside.infrastructure;

import eventside.domain.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EventRepository {

    private List<Event> events = new ArrayList<>();
    private Map<String,Subscriber> subscribers = Map.of("http://localhost:8082", new Subscriber("http://localhost:8082"));


    public void processEvent(Event event) {
        events.add(event);
        for (Subscriber s: subscribers.values()) {
            s.notify(event);
        }
    }

    public void attach(String host){
        Subscriber sub = new Subscriber(host);
        subscribers.put(host,sub);
        for (Event e:events) {
            sub.notify(e);
        }
        System.out.println("[EVENT] Attached new Subscriber: "+host);
    }
    
    public void detach(String host){
        subscribers.remove(host);
        System.out.println("[EVENT] Removed Subscriber: "+host);
    }
}
