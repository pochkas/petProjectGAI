package org.example.service;

import org.example.dto.FineDTO;
import org.example.entity.FineEntity;
import org.example.exception.FineIsAlreadyExistException;
import org.example.exception.FineNotFoundException;
import org.example.repository.FineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MappingService {

    @Autowired
    private FineRepository fineRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    FineService fineService;

    private FineDTO convertDataIntoDTO(FineEntity fine) {




        FineDTO dto = new FineDTO();

        dto.setFineId(fine.getFineId());
        dto.setFine(fine.getFine());
        dto.setCarNumber(fine.getCarNumber());
        dto.setUsername(fine.getUsername());
        dto.setUsernameGai(fine.getUsernameGai());
        dto.setProtocolDate(fine.getProtocolDate());
        dto.setFine(fine.getFine());
        dto.setFineWasPaid(fine.isFineWasPaid());
        dto.setDatePaid(fine.getDatePaid());
        dto.setLastDayToPay(fine.getLastDayToPay());
        dto.setCourt(fine.isCourt());


        return dto;
    }

    public List<FineDTO> getAllFine(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2147483647") int size, @RequestParam(defaultValue = "fineId,asc") String[] sort) {

            return ((List<FineEntity>) fineService
                    .findAll(page, size, sort))
                    .stream()
                    .map(this::convertDataIntoDTO)
                    .collect(Collectors.toList());



    }

    public FineDTO addFine(FineDTO fineDTO) throws FineIsAlreadyExistException {



        FineEntity fineRequest= modelMapper.map(fineDTO, FineEntity.class);

        FineEntity fine=fineService.addFine(fineRequest);

        fineRepository.save(fine);

        FineDTO fineResponse=modelMapper.map(fine, FineDTO.class);

        return fineResponse;


    }


    public FineDTO update(@RequestBody FineDTO fineDTO) {

        FineEntity fineRequest=modelMapper.map(fineDTO, FineEntity.class);
        FineEntity fine=fineService.update(fineRequest);
        FineDTO fineResponse=modelMapper.map(fine, FineDTO.class);
        return fineResponse;


    }


    public ResponseEntity<Long> delete(@RequestBody Long fineId) throws FineNotFoundException {


        FineEntity fine = fineRepository.findById(fineId).get();
        if (fine == null) {
            throw new FineNotFoundException("Данный пользователь не найден");
        }
        fineRepository.deleteById(fineId);
        return ResponseEntity.ok().body(fineId);
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
