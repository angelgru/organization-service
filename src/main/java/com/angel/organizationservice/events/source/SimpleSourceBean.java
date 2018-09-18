package com.angel.organizationservice.events.source;

import com.angel.organizationservice.events.channels.CustomChannels;
import com.angel.organizationservice.events.models.OrganizationChangedModel;
import com.angel.organizationservice.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
public class SimpleSourceBean {

    private CustomChannels messageSource;
    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(CustomChannels messageSource) {
        this.messageSource = messageSource;
    }

    public void publishOrgChange(String action, String orgId) {
        logger.error("Sending Kafka message {} for Organization Id: {} ", action, orgId);
        OrganizationChangedModel change = new OrganizationChangedModel(
                OrganizationChangedModel.class.getTypeName(),
                action,
                orgId,
                UserContext.getCorrelationId()
        );
        messageSource.organizationChannel().send(MessageBuilder.withPayload(change).build());
    }
}
