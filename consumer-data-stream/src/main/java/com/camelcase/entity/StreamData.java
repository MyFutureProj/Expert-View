package com.camelcase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ravi Panchal
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event_data_dump")
public class StreamData {
    @Id
    private ObjectId id;
    private String eventData;
}


