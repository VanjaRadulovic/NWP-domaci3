package rs.raf.demo.schedule;

import rs.raf.demo.model.ErrorMsg;
import rs.raf.demo.model.Machine;
import rs.raf.demo.model.Status;
import rs.raf.demo.repositories.ErrorMsgRepository;
import rs.raf.demo.repositories.MachineRepository;
import rs.raf.demo.services.AsinhronComponent;

import java.util.Date;
import java.util.Optional;

public class Restart implements Runnable{

    private MachineRepository machineRepository;
    private Long machineId;
    private Long userId;

    private AsinhronComponent ac;

    private ErrorMsgRepository errorMsgRepository;

    public Restart(MachineRepository machineRepository, Long machineId, Long userId, AsinhronComponent ac, ErrorMsgRepository errorMsgRepository) {
        this.machineRepository = machineRepository;
        this.machineId = machineId;
        this.userId = userId;
        this.ac = ac;
        this.errorMsgRepository = errorMsgRepository;
    }

    @Override
    public void run() {
        Optional<Machine> machine = machineRepository.findById(this.machineId);
        if (machine.isPresent())
        {

            if(!machine.get().isExists()){
                ErrorMsg errorMsg = new ErrorMsg(this.machineId, "Machine doesn't exist","Start",new Date(),this.userId);
                errorMsgRepository.save(errorMsg);
                return;
            }
            if(machine.get().isWorking()){
                ErrorMsg errorMsg = new ErrorMsg(this.machineId, "Machine is already working","Start",new Date(),this.userId);
                errorMsgRepository.save(errorMsg);
                return;
            }
            if(machine.get().getStatus().equals(Status.STOPPED)){
                ErrorMsg errorMsg = new ErrorMsg(this.machineId, "Machine is stopped","Start",new Date(),this.userId);
                errorMsgRepository.save(errorMsg);
                return;
            }

            machine.get().setWorking(true);
            machineRepository.save(machine.get());
            ac.restartMachine(machine.get());
        }
        else
        {
            ErrorMsg errorMsg = new ErrorMsg(this.machineId, "Not Found","Start",new Date(),this.userId);
            errorMsgRepository.save(errorMsg);
        }
    }
}
