package com.adtech.corona.service;

import com.adtech.corona.model.CoronaData;

import java.util.Date;
import java.util.List;

public interface CoronaDataService {
    CoronaData save(CoronaData coronaData);

    CoronaData findById(Long id);

    CoronaData findByTime(String id);

    CoronaData findByDate(Date date1, Date date2);

    List<CoronaData> findAll();

    CoronaData findLatest();
}
