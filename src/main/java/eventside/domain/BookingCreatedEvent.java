package eventside.domain;

import eventside.domain.Event;
import writeside.domain.Booking;
import writeside.domain.PersonalDetails;
import writeside.domain.Room;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Set;

public class BookingCreatedEvent extends Event {


    private String customer;
    private long timestamp;
    private String content;

    public BookingCreatedEvent(String customer, long timestamp, String content) {
        this.customer = customer;
        this.timestamp = timestamp;
        this.content = content;


    }

    public String getCustomer() {
        return customer;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

}

