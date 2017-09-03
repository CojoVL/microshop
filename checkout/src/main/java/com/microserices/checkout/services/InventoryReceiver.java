package com.microserices.checkout.services;

import com.microserices.checkout.models.InventoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class InventoryReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryReceiver.class);

    @StreamListener("stockChannel")
    public void onReceiving(final Message<InventoryModel> message) {
        InventoryModel inventory = message.getPayload();
        LOGGER.info("Acknowledgment provided: " + inventory.toString());
    }

}
