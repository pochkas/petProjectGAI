package org.example;



import java.time.LocalDateTime;
import java.util.Date;

public class FineWithId extends Fine{


    private  Integer fineId;
    public int getFineId() {
        return fineId;
    }

    public void setFineId(int fineId) {
        this.fineId = fineId;
    }


    @Override
    public String getCarNumber() {
        return super.getCarNumber();
    }

    @Override
    public void setCarNumber(String carNumber) {
        super.setCarNumber(carNumber);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getUsernameGai() {
        return super.getUsernameGai();
    }

    @Override
    public void setUsernameGai(String usernameGai) {
        super.setUsernameGai(usernameGai);
    }

    @Override
    public Date getProtocolDate() {
        return super.getProtocolDate();
    }

    @Override
    public void setProtocolDate(Date protocolDate) {
        super.setProtocolDate(protocolDate);
    }

    @Override
    public Double getFine() {
        return super.getFine();
    }

    @Override
    public void setFine(Double fine) {
        super.setFine(fine);
    }

    @Override
    public boolean isCourt() {
        return super.isCourt();
    }

    @Override
    public void setCourt(boolean court) {
        super.setCourt(court);
    }

    @Override
    public boolean isFineWasPaid() {
        return super.isFineWasPaid();
    }

    @Override
    public void setFineWasPaid(boolean fineWasPaid) {
        super.setFineWasPaid(fineWasPaid);
    }

    @Override
    public LocalDateTime getDatePaid() {
        return super.getDatePaid();
    }

    @Override
    public void setDatePaid(LocalDateTime datePaid) {
        super.setDatePaid(datePaid);
    }

    @Override
    public Date getLastDayToPay() {
        return super.getLastDayToPay();
    }

    @Override
    public void setLastDayToPay(Date lastDayToPay) {
        super.setLastDayToPay(lastDayToPay);
    }
}
