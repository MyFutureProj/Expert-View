package com.camelcase.service;

import com.camelcase.entity.StreamData;
import com.camelcase.repository.StreamDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ravi Panchal
 */
@Service
public class StreamDataServiceImpl implements StreamDataService{

    @Autowired
    private StreamDataRepository streamDataRepository;

    @Override
    public void saveConsumedData(StreamData streamData) {
        streamDataRepository.save(streamData);
    }
}
