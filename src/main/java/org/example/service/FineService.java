package org.example.service;

import org.example.entity.FineEntity;
import org.example.exception.FineIsAlreadyExistException;
import org.example.exception.FineNotFoundException;
import org.example.repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class FineService {

    @Autowired
    private FineRepository fineRepository;


    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }


    public List<FineEntity> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2147483647") int size, @RequestParam(defaultValue = "fineId,asc") String[] sort) {

        PageRequest pr = PageRequest.of(page, size);
        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }

        return fineRepository.findAll(pr.of(page, size, (Sort.by(orders)))).toList();

    }


    public FineEntity addFine(FineEntity fine) throws FineIsAlreadyExistException {

        return fineRepository.save(fine);


    }


    public FineEntity update(@RequestBody FineEntity fine) {

        return fineRepository.save(fine);

    }


    public Long delete(@RequestBody Long fineId) throws FineNotFoundException {

        FineEntity fine = fineRepository.findById(fineId).get();
        if (fine == null) {
            throw new FineNotFoundException("Данный пользователь не найден");
        }
        fineRepository.deleteById(fineId);
        return fineId;
    }


    public FineEntity getFine(@PathVariable Long fineId) throws FineNotFoundException {
        FineEntity fine = fineRepository.findById(fineId).get();
        if (fine == null) {
            throw new FineNotFoundException("Данный пользователь не найден");
        }
        return fineRepository.findById(fineId).get();

    }


    public void pay(@PathVariable Long fineId) {

        FineEntity fine = fineRepository.findById(fineId).get();
        fine.setFineWasPaid(true);
        fineRepository.save(fine);

    }


    public void court(@PathVariable Long fineId) {
        FineEntity fine = fineRepository.findById(fineId).get();
        fine.setCourt(true);
        fineRepository.save(fine);
    }

}
