package com.camelcase.service;

import com.camelcase.entity.StreamData;

/**
 * @author Ravi Panchal
 */
public interface StreamDataService {
    void saveConsumedData(StreamData streamData);
}
