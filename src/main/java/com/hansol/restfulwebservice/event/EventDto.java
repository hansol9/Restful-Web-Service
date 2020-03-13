package com.hansol.restfulwebservice.event;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class EventDto {

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
}
