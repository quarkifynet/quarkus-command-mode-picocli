package org.acme.getting.started;

import org.acme.getting.started.data.Setting;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    public String greeting(String name) {
        return Setting.find("key = 'greet_phrase'").<Setting>firstResult().value + name;
    }

}
