/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.shipping;

/**
 * A repository interface that should be implemented by
 * the various data store integrations.
 */
public interface ShipmentRepository {
    /**
     * Return shipment for the specified order.
     *
     * @param orderId the order identifier
     *
     * @return the shipment for the specified order;
     *         {@code null} if the shipment doesn't exist
     */
    Shipment getShipment(String orderId);

    /**
     * Save shipment details into the repository.
     *
     * @param shipment the shipment to save
     */
    void saveShipment(Shipment shipment);
}
