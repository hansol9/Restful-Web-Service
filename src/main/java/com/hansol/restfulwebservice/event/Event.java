package com.hansol.restfulwebservice.event;

import com.hansol.restfulwebservice.event.accounts.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Event {

    @Id @GeneratedValue
    private Integer id;

    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location;    //optional
    private int basePrice;      //optional
    private int maxPrice;       //optional
    private int limitOfEnrollment;

    private boolean offline;
    private boolean free;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus = EventStatus.DRAFT;

    @ManyToOne
    private Account manager;

    public void update() {

        // update free
        if (this.basePrice == 0 && this.maxPrice==0) {
            this.free = true;
        }
        else {
            this.free = false;
        }

        // update location
        if (this.location == null || this.location.isBlank()) {
            this.offline = false;
        }
        else {
            this.offline = true;
        }
    }
}
