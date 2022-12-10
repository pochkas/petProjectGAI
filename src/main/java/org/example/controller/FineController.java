package org.example.controller;

import org.example.model.Fine;
import org.example.repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@RestController
public class FineController {

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

    @GetMapping(value = "/api/v1/fines")
    public List<Fine> findAll( @RequestParam(required = false) String fines, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2147483647") int size, @RequestParam(defaultValue = "fineId,asc") String[] sort) {
         PageRequest pr = PageRequest.of(page,size);


        List<Order> orders = new ArrayList<Order>();


        if (sort[0].contains(",")) {

            for (String sortOrder : sort) {

                String[] _sort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {

            orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }

        return fineRepository.findAll(pr.of(page, size,(Sort.by(orders)))).toList();


    }





    @PostMapping(value = "/api/v1/fines")
    public Fine addFine(@RequestBody Fine fine) {

        return fineRepository.save(fine);

    }

    @PutMapping(value = "/api/v1/fines")
    public Fine update(@RequestBody Fine fine) {
        return fineRepository.save(fine);

    }

    @DeleteMapping(value = "/api/v1/fines")
    public void delete(@RequestBody Long fineId) {
        fineRepository.deleteById(fineId);

    }

    @GetMapping(value = "api/v1/fines/{fineId}")
    public Fine getFine(@PathVariable Long fineId) {
        return fineRepository.findById(fineId).get();

    }

    @PatchMapping(value = "/api/v1/fines/{fineId}/pay")
    public void pay(@PathVariable Long fineId) {

        Fine fine = fineRepository.findById(fineId).get();
        fine.setFineWasPaid(true);
        fineRepository.save(fine);

    }

    @PatchMapping(value = "/api/v1/fines/{fineId}/court")
    public void court(@PathVariable Long fineId) {
        Fine fine = fineRepository.findById(fineId).get();
        fine.setCourt(true);
        fineRepository.save(fine);
    }


}