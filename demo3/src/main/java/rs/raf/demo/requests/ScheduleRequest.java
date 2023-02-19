package rs.raf.demo.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ScheduleRequest {


    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
