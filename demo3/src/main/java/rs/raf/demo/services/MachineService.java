package rs.raf.demo.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import rs.raf.demo.model.Machine;
import rs.raf.demo.model.Status;
import rs.raf.demo.repositories.ErrorMsgRepository;
import rs.raf.demo.repositories.MachineRepository;
import rs.raf.demo.schedule.Restart;
import rs.raf.demo.schedule.Start;
import rs.raf.demo.schedule.Stop;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MachineService {

    private final MachineRepository machineRepository;
    private final TaskScheduler taskScheduler;
    private final AsinhronComponent asinhronComponent;

    private final ErrorMsgRepository errorMsgRepository;

    public MachineService(MachineRepository machineRepository, TaskScheduler taskScheduler, AsinhronComponent asinhronComponent, ErrorMsgRepository errorMsgRepository) {
        this.machineRepository = machineRepository;
        this.taskScheduler = taskScheduler;
        this.asinhronComponent = asinhronComponent;
        this.errorMsgRepository = errorMsgRepository;
    }


    public List<Machine> findAll(Long id) {
        return this.machineRepository.findMachinesByUserId(id);
    }

    public Machine save(Machine machine){

        System.out.println(machine.toString());
        return this.machineRepository.save(machine);
    }

    public void delete(Long id){
        Optional<Machine> machine = this.machineRepository.findById(id);
        Machine mach;
        if(machine.isPresent())
        {
            mach = machine.get();
            mach.setExists(false);
            this.machineRepository.save(mach);
        }
    }

    public Optional<Machine> findbyID(Long id){return this.machineRepository.findById(id);}

    public List<Machine> search (String email, List<Status> status, String machineName, LocalDate dateFrom, LocalDate dateTo){
        return this.machineRepository.searchMachines(email,status,machineName,dateFrom,dateTo);
    }

    public ResponseEntity<?> start(Long id){
        Optional<Machine> machine = machineRepository.findById(id);

        if(machine.isPresent())
        {

            if(machine.get().getStatus().equals(Status.RUNNING)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Machine must be stopped to start it");
            }
            if(machine.get().isWorking()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Machine is working");
            }

            Machine m = machine.get();
            m.setWorking(true);
            machineRepository.save(m);
            asinhronComponent.startMachine(m);
            return ResponseEntity.ok(m);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Machine not found");

    }

    public ResponseEntity<?> stop(Long id){
        Optional<Machine> machine = machineRepository.findById(id);

        if(machine.isPresent())
        {

            if(machine.get().getStatus().equals(Status.STOPPED)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Machine must be running to stop it");
            }
            if(machine.get().isWorking()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Machine is stopped");
            }

            Machine m = machine.get();
            m.setWorking(true);
            machineRepository.save(m);
            asinhronComponent.stopMachine(m);
            return ResponseEntity.ok(m);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Machine not found");

    }

    public ResponseEntity<?> restart(Long id){
        Optional<Machine> machine = machineRepository.findById(id);

        if(machine.isPresent())
        {

            if(machine.get().getStatus().equals(Status.STOPPED)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Machine must be running to restart it");
            }
            if(machine.get().isWorking()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Machine is stopped");
            }

            Machine m = machine.get();
            m.setWorking(true);
            machineRepository.save(m);
            asinhronComponent.restartMachine(m);
            return ResponseEntity.ok(m);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Machine not found");

    }

    public ResponseEntity<?> scheduleStart(Long machineId, Long userId, Date date){

        taskScheduler.schedule(new Start(machineRepository,machineId,userId,asinhronComponent,errorMsgRepository),date);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> scheduleStop(Long machineId, Long userId, Date date){

        taskScheduler.schedule(new Stop(machineRepository,machineId,userId,asinhronComponent,errorMsgRepository),date);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> scheduleRestart(Long machineId, Long userId, Date date){

        taskScheduler.schedule(new Restart(machineRepository,machineId,userId,asinhronComponent,errorMsgRepository),date);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
