/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.shipping;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Shipping address.
 */
@Data
@NoArgsConstructor
@Schema(description = "Shipping address")
public class Address implements Serializable {
    /**
     * Street number.
     */
    @Schema(description = "Street number")
    private String number;

    /**
     * Street name.
     */
    @Schema(description = "Street name")
    private String street;

    /**
     * City name.
     */
    @Schema(description = "City name")
    private String city;

    /**
     * Postal code.
     */
    @Schema(description = "Postal code")
    private String postcode;

    /**
     * Country name.
     */
    @Schema(description = "Country name")
    private String country;

    @Builder
    Address(String number, String street, String city, String postcode, String country) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
    }
}
