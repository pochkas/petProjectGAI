package org.example.controller;
import org.example.DB;
import org.example.FineId;
import org.example.FineWithId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.example.Fine;

import java.util.List;


    @RestController
    public class FineController {

        @Autowired
        private DB db;

        @GetMapping(value = "/api/v1/fines")
        public @ResponseBody List<FineWithId> getAllFines() {
            return db.getAll();

        }

        @PostMapping(value = "/api/v1/fines")
        public @ResponseBody int addFine(@RequestBody Fine fine) {

            return db.create(fine);

        }

        @PutMapping(value = "/api/v1/fines")
        public @ResponseBody void update(@RequestBody FineWithId fineWithId) {
            db.update(fineWithId);

        }

        @DeleteMapping(value = "/api/v1/fines")
        public void delete(@RequestBody FineId fineId) {
            db.delete(fineId);

        }

        @GetMapping(value = "api/v1/fines/{fineId}")
        public @ResponseBody Fine getFine(@PathVariable Integer fineId) {
            return db.getFine(fineId);

        }

        @PatchMapping(value = "/api/v1/fines/{fineId}/pay")
        public @ResponseBody void pay(@PathVariable Integer fineId) {
            db.fineWasPaid(fineId);

        }

        @PatchMapping(value = "/api/v1/fines/{fineId}/court")
        public @ResponseBody void court(@PathVariable Integer fineId) {
            db.court(fineId);

        }
    }