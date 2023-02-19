package rs.raf.demo.requests;

import rs.raf.demo.model.Status;

import java.time.LocalDate;
import java.util.List;

public class SearchMachineRequest {

    private String machineName;
    private List<Status> status;
    private  LocalDate dateFrom;
    private LocalDate dateTo;

//    public SearchMachineRequest(String machineName, String email, List<Status> status, LocalDate dateFrom, LocalDate dateTo) {
//        this.machineName = machineName;
//        this.email = email;
//        this.status = status;
//        this.dateFrom = dateFrom;
//        this.dateTo = dateTo;
//    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
