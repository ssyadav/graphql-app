package com.satya.graphql.lec14.controller;

import com.satya.graphql.lec14.dto.CustomerEvent;
import com.satya.graphql.lec14.service.CustomerEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerEventController {

    @Autowired private CustomerEventService eventService;

    @SubscriptionMapping
    public Flux<CustomerEvent> customerEvents() {
        return this.eventService.subscribe();
    }


/*
    subscription {
        customerEvents {
            id
                    action
        }
    }
*/
}
