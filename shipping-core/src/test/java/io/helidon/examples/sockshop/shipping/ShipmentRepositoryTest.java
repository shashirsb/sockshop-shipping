/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.shipping;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.helidon.examples.sockshop.shipping.TestDataFactory.shipment;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * Abstract base class containing tests for all
 * {@link io.helidon.examples.sockshop.shipping.ShipmentRepository} implementations.
 */
public abstract class ShipmentRepositoryTest {

    private final TestShipmentRepository shipments = getShipmentRepository();

    protected abstract TestShipmentRepository getShipmentRepository();

    @BeforeEach
    void setup() {
        shipments.clear();
    }

    @Test
    void testSaveShipment() {
        LocalDate deliveryDate = LocalDate.now().plusDays(2);
        shipments.saveShipment(shipment("A123", "UPS", "1Z999AA10123456784", deliveryDate));

        Shipment s = shipments.getShipment("A123");
        assertThat(s.getOrderId(), is("A123"));
        assertThat(s.getCarrier(), is("UPS"));
        assertThat(s.getTrackingNumber(), is("1Z999AA10123456784"));
        assertThat(s.getDeliveryDate(), is(deliveryDate));
    }


    @Test
    void testGetShipmentByOrder() {
        assertThat(shipments.getShipment("A123"), nullValue());

        LocalDate deliveryDate = LocalDate.now().plusDays(2);
        shipments.saveShipment(shipment("A123", "UPS", "1Z999AA10123456784", deliveryDate));

        Shipment s = shipments.getShipment("A123");
        assertThat(s.getOrderId(), is("A123"));
        assertThat(s.getCarrier(), is("UPS"));
        assertThat(s.getTrackingNumber(), is("1Z999AA10123456784"));
        assertThat(s.getDeliveryDate(), is(deliveryDate));
    }
}
