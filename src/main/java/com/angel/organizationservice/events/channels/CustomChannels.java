package com.angel.organizationservice.events.channels;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomChannels {

    @Output("organizationChannel")
    MessageChannel organizationChannel();
}
