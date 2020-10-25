/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.shipping;

import java.time.LocalDate;

/**
 * Helper methods to create test data.
 */
class TestDataFactory {

    static ShippingRequest shippingRequest(String orderId, int itemCount) {
        return ShippingRequest.builder()
                .orderId(orderId)
                .address(address())
                .itemCount(itemCount)
                .build();
    }

    static Address address() {
        return Address.builder()
                .number("123")
                .street("Main St")
                .city("Springfield")
                .postcode("55555")
                .country("USA")
                .build();
    }

    static Shipment shipment(String orderId, String carrier, String trackingNumber, LocalDate deliveryDate) {
        return Shipment.builder()
                .orderId(orderId)
                .carrier(carrier)
                .trackingNumber(trackingNumber)
                .deliveryDate(deliveryDate)
                .build();
    }
}
