package org.example.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;


@Data
public class FineDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fineId;

    private String carNumber;

    private String username;

    private String usernameGai;

    private Date protocolDate;

    private Double fine;

    private boolean court;

    private boolean fineWasPaid;

    private LocalDateTime datePaid;

    private Date lastDayToPay;



    public Long getFineId() {
        return fineId;
    }

    public void setFineId(Long fineId) {
        this.fineId = fineId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameGai() {
        return usernameGai;
    }

    public void setUsernameGai(String usernameGai) {
        this.usernameGai = usernameGai;
    }

    public Date getProtocolDate() {
        return protocolDate;
    }

    public void setProtocolDate(Date protocolDate) {
        this.protocolDate = protocolDate;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    public boolean isCourt() {
        return court;
    }

    public void setCourt(boolean court) {
        this.court = court;
    }

    public boolean isFineWasPaid() {
        return fineWasPaid;
    }

    public void setFineWasPaid(boolean fineWasPaid) {
        this.fineWasPaid = fineWasPaid;
    }

    public LocalDateTime getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDateTime datePaid) {
        this.datePaid = datePaid;
    }

    public Date getLastDayToPay() {
        return lastDayToPay;
    }

    public void setLastDayToPay(Date lastDayToPay) {
        this.lastDayToPay = lastDayToPay;
    }
}
