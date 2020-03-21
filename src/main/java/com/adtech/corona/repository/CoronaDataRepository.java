package com.adtech.corona.repository;

import com.adtech.corona.model.CoronaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Repository
public interface CoronaDataRepository extends JpaRepository<CoronaData, Serializable> {
    CoronaData findById(Long id);

    CoronaData findByTime(String time);

    CoronaData findByCreatedDateBetween(Date date1, Date date2);
}
