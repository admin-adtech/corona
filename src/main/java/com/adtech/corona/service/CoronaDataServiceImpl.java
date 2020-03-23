package com.adtech.corona.service;

import com.adtech.corona.model.CoronaData;
import com.adtech.corona.repository.CoronaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class CoronaDataServiceImpl implements CoronaDataService {

    @Autowired
    private CoronaDataRepository coronaDataRepository;


    @Override
    public CoronaData save(CoronaData coronaData) {
        return coronaDataRepository.save(coronaData);
    }

    @Override
    public CoronaData findById(Long id) {
        return coronaDataRepository.findById(id);
    }

    @Override
    public CoronaData findByTime(String time) {
        return coronaDataRepository.findByTime(time);
    }

    @Override
    public CoronaData findByDate(Date date1, Date date2) {
        if (date2 == null) date2 = new Date();
        return coronaDataRepository.findByCreatedDateBetween(date1, date2);
    }

    @Override
    public List<CoronaData> findAll() {
        return coronaDataRepository.findAll();
    }

    @Override
    public CoronaData findLatest() {
        List<CoronaData> coronaDataList = coronaDataRepository.findAll();
        Collections.sort(coronaDataList, Comparator.comparing(CoronaData::getCreatedDate).reversed());
        return coronaDataList.get(0);
    }
}
