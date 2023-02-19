package rs.raf.demo.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rs.raf.demo.model.Machine;
import rs.raf.demo.model.Status;
import rs.raf.demo.repositories.MachineRepository;

@Component
public class AsinhronComponent {

    private final MachineRepository machineRepository;

    public AsinhronComponent(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Async
    public void startMachine(Machine machine){

        long time = 10000;
        try {
            Thread.sleep(time);
        }
        catch (Exception e){
            System.out.println("Start Error");
        }

        machine.setStatus(Status.RUNNING);
        machine.setWorking(false);
        machineRepository.save(machine);

    }

    @Async
    public void stopMachine(Machine machine){

        long time = 10000;
        try {
            Thread.sleep(time);
        }
        catch (Exception e){
            System.out.println("Stop Error");
        }

        machine.setStatus(Status.STOPPED);
        machine.setWorking(false);
        machineRepository.save(machine);

    }

    @Async
    public void restartMachine(Machine machine){

        long time = 10000;

        try {
            Thread.sleep(time);
        }
        catch (Exception e){
            System.out.println("Restart1 Error");
        }

        machine.setStatus(Status.STOPPED);
        machineRepository.save(machine);

        try {
            Thread.sleep(time);
        }
        catch (Exception e){
            System.out.println("Restart2 Error");
        }

        machine.setStatus(Status.RUNNING);
        machine.setWorking(false);
        machineRepository.save(machine);

    }
}
