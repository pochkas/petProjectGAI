package org.example;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DB {



    public List<FineWithId> fines = new ArrayList<>();

    public DB(List<FineWithId> fines) {
        this.fines = fines;
    }

    public DB() {

    }

    public List<FineWithId> getAll() {
        return fines;
    }

    public Fine getFine(int id) {
        return fines.get(id);
    }

    public int create(Fine fine) {
        FineWithId fineWithId=new FineWithId();

        fineWithId.setCourt(fine.isCourt());
        fineWithId.setCarNumber(fine.getCarNumber());
        fineWithId.setDatePaid(fine.getDatePaid());
        fineWithId.setFine(fine.getFine());
        fineWithId.setDatePaid(fine.getDatePaid());
        fineWithId.setLastDayToPay(fine.getLastDayToPay());
        fineWithId.setFineWasPaid(fine.isFineWasPaid());
        fineWithId.setProtocolDate(fine.getProtocolDate());
        fineWithId.setUsername(fine.getUsername());
        fine.setUsernameGai(fine.getUsernameGai());

        fines.add(fineWithId);

        fineWithId.setFineId(fines.size() - 1);

        return fineWithId.getFineId();
    }

    public void update(FineWithId fineWithId) {
        fines.set(fineWithId.getFineId(), fineWithId);

    }

    public void delete(FineId fineId) {
        fines.set(fineId.getFineId(), null);

    }

    public void fineWasPaid(int id) {

        fines.get(id).setFineWasPaid(true);
        fines.get(id).setDatePaid(LocalDateTime.now());

    }

    public void court(int id) {
        fines.get(id).setCourt(true);


    }
}