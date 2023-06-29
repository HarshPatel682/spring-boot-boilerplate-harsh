package org.baps.api.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DemoApiClient {

    @Autowired
    @Qualifier("client1")
    private WebClient client1;

    public void testClient() {
        // TODO: CLIENT CALL GOES HERE
    }

}
