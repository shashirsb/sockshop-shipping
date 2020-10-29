/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.shipping.atpsoda;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import io.helidon.examples.sockshop.shipping.Shipment;
import io.helidon.examples.sockshop.shipping.ShipmentRepository;

import com.mongodb.client.MongoCollection;

import org.eclipse.microprofile.opentracing.Traced;

import static com.mongodb.client.model.Filters.eq;
import static javax.interceptor.Interceptor.Priority.APPLICATION;


///////////////////

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import org.bson.BsonDocument;
import org.bson.conversions.Bson;
import org.eclipse.microprofile.opentracing.Traced;


import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


import io.helidon.config.Config;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import java.io.*;
import java.util.Properties;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import oracle.soda.rdbms.OracleRDBMSClient;
import oracle.soda.OracleDatabase;
import oracle.soda.OracleCursor;
import oracle.soda.OracleCollection;
import oracle.soda.OracleDocument;
import oracle.soda.OracleException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;


/**
 * An implementation of {@link io.helidon.examples.sockshop.shipping.ShipmentRepository}
 * that that uses MongoDB as a backend data store.
 */
@ApplicationScoped
@Alternative
@Priority(APPLICATION)
@Traced
public class AtpSodaShipmentRepository implements ShipmentRepository {
    public static AtpSodaProducers asp = new AtpSodaProducers();
    public static OracleDatabase db = asp.dbConnect();

    @Inject
    AtpSodaShipmentRepository() {
        try {
            String UserResponse = createData();
            System.out.println(UserResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Shipment getShipment(String orderId) {
        //return shipments.find(eq("orderId", orderId)).first();

        Shipment shipments = new Shipment();

        org.json.simple.JSONObject _jsonObject = new JSONObject();
        org.json.simple.parser.JSONParser _parser = new JSONParser();


        try {
      
            OracleCollection col = this.db.admin().createCollection("shipments");

            // Find a documents in the collection.
            OracleDocument filterSpec =
                this.db.createDocumentFromString("{ \"orderId\" : \"" + orderId + "\"}");
            OracleCursor c = col.find().filter(filterSpec).getCursor();
            String jsonFormattedString = null;
            try {
                OracleDocument resultDoc;

                while (c.hasNext()) {

                    // String orderId, String carrier, String trackingNumber, LocalDate deliveryDate
                    resultDoc = c.next();
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(resultDoc.getContentAsString());
                    JSONObject jsonObject = (JSONObject) obj;
                    shipments.orderId = jsonObject.get("orderId").toString();
                    shipments.carrier = jsonObject.get("carrier").toString();
                    shipments.trackingNumber = jsonObject.get("trackingNumber").toString();


                     // String str = "2020-10-29T14:17:02.216+00:00"; 
                     String strDatewithTime = jsonObject.get("deliveryDate").toString();
                     LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);

                     shipments.deliveryDate = aLDT;
                                    }
            } finally {
                // IMPORTANT: YOU MUST CLOSE THE CURSOR TO RELEASE RESOURCES.
                if (c != null) c.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Shipment getShipment.. GET Request 200OK");
        return shipments;

    }

    @Override
    public void saveShipment(Shipment shipment) {
        //shipments.insertOne(shipment);
        //String orderId, String carrier, String trackingNumber, LocalDate deliveryDate

        try {

            OracleCollection col = this.db.admin().createCollection("shipments");
            String _document = "{\"orderId\":\"" + auth.orderId.toString() + "\",\"deliveryDate\":\"" + auth.deliveryDate + "\",\"carrier\":\"" + auth.carrier.toString() + "\",\"trackingNumber\":\"" + auth.trackingNumber.toString() + "\"}";
            System.out.println(_document);
    
            // Create a JSON document.
            OracleDocument doc =
                this.db.createDocumentFromString(_document);

            // Insert the document into a collection.
            col.insert(doc);
            System.out.println("saveShipment .... 200OK");
        } catch (OracleException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createData() {

        try {

            OracleCollection col = this.db.admin().createCollection("shipments");

            col.admin().truncate();


        } catch (OracleException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "successfully created payments collection !!!";
    }
}
