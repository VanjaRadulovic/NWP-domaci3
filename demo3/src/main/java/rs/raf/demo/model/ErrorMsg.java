package rs.raf.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "errors")
public class ErrorMsg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="machineId")
  //  @NotNull
    private Long machineId;
    @Column(name="userId")
  //  @NotNull
    private Long userId;
    @Column(name="date")
  //  @NotNull
    private Date date;
    @Column(name="errorType")
  // @NotNull
    private String errorType;
    @Column(name="errorMsg")
   // @NotNull
    private String errorMsg;


    public ErrorMsg(Long machineId,String errorMsg, String errorType, Date date, Long userId) {
        this.machineId = machineId;
        this.date = date;
        this.errorType = errorType;
        this.errorMsg = errorMsg;
        this.userId = userId;
    }

    public ErrorMsg() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
