package org.example.controller;

import org.example.dto.FineDTO;
import org.example.entity.FineEntity;
import org.example.service.FineService;
import org.example.service.MappingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/fines")
public class FineController {

    @Autowired
    private FineService fineService;

    @Autowired
    private MappingService mappingService;

    @Autowired
    private ModelMapper modelMapper;



    @GetMapping()
    public ResponseEntity findAll(@RequestParam(required = false) String fines, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2147483647") int size, @RequestParam(defaultValue = "fineId,asc") String[] sort) {

        try {

            return ResponseEntity.ok(mappingService.getAllFine(page, size, sort));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Fines have not been found");
        }

    }


    @PostMapping()
    public ResponseEntity addFine(@RequestBody FineDTO fineDTO) {
        try {
            FineEntity fineRequest= modelMapper.map(fineDTO, FineEntity.class);

            FineEntity fine=fineService.addFine(fineRequest);

            FineDTO fineResponse=modelMapper.map(fine, FineDTO.class);

            return  new ResponseEntity<FineDTO>(fineResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Fine has not been added");
        }
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody FineDTO fineDTO) {
        try {
            FineEntity fineRequest= modelMapper.map(fineDTO, FineEntity.class);

            FineEntity fine=fineService.addFine(fineRequest);

            FineDTO fineResponse=modelMapper.map(fine, FineDTO.class);

            return  new ResponseEntity<FineDTO>(fineResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Fine has not been updated");
        }

    }

    @DeleteMapping()
    public ResponseEntity delete(@RequestBody Long fineId) {
        try {
            fineService.delete(fineId);
            return ResponseEntity.ok("Fine was deleted");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Fine was not deleted");
        }


    }

    @GetMapping(value = "/{fineId}")
    public ResponseEntity getFine(@PathVariable Long fineId) {
        try {
            return ResponseEntity.ok(fineService.getFine(fineId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Fine has not been found");
        }

    }

    @PatchMapping(value = "/{fineId}/pay")
    public ResponseEntity pay(@PathVariable Long fineId) {
        try {
            fineService.pay(fineId);
            return ResponseEntity.ok("Fine was payed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Fine was not payed");
        }


    }

    @PatchMapping(value = "/{fineId}/court")
    public ResponseEntity court(@PathVariable Long fineId) {
        try {
            fineService.court(fineId);
            return ResponseEntity.ok("Sent to court");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Was not sent to court");
        }

    }


}