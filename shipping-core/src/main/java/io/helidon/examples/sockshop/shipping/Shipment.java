/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.shipping;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Shipment information to send as a response to Order service.
 */
@Data
@NoArgsConstructor
@Entity
@Schema(description = "Shipment information to send as a response to Order service")
public class Shipment implements Serializable {
    /**
     * Order identifier.
     */
    @Id
    @Schema(description = "Order identifier")
    public String orderId;

    /**
     * Shipping carrier.
     */
    @Schema(description = "Shipping carrier")
    public String carrier;

    /**
     * Tracking number.
     */
    @Schema(description = "racking number")
    public String trackingNumber;

    /**
     * Estimated delivery date.
     */
    @Schema(description = "Estimated delivery date")
    public LocalDate deliveryDate;

    @Builder
    Shipment(String orderId, String carrier, String trackingNumber, LocalDate deliveryDate) {
        this.orderId = orderId;
        this.carrier = carrier;
        this.trackingNumber = trackingNumber;
        this.deliveryDate = deliveryDate;
    }
}
