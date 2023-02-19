package rs.raf.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.raf.demo.model.Machine;
import rs.raf.demo.model.Status;
import rs.raf.demo.model.User;
import rs.raf.demo.requests.*;
import rs.raf.demo.services.AsinhronComponent;
import rs.raf.demo.services.MachineService;
import rs.raf.demo.services.UserService;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/machines")
@CrossOrigin
public class MachineController {

    private final MachineService machineService;
    private final UserService userService;



    public MachineController(MachineService machineService, UserService userService) {
        this.machineService = machineService;
        this.userService = userService;

    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Machine> getAllMachines(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName()).get();
        Long id = user.getUserId();
        return machineService.findAll(id);
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMachine(@Valid @RequestBody CreateMachineRequest createMachine){
        System.out.println("sup bitch");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName()).get();

        System.out.println(user.getEmail());
        System.out.println(createMachine.getName());

        Machine machine = new Machine();
        machine.setName(createMachine.getName());
        machine.setDate(LocalDate.now());
        machine.setWorking(false);
        machine.setStatus(Status.STOPPED);
        machine.setExists(true);
        machine.setUser(user);


        //return new ResponseEntity<>(machineService.save(machine), HttpStatus.OK);
        return ResponseEntity.ok(machineService.save(machine));
    }

    @DeleteMapping(value = "/del/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        Optional<Machine> machine = (machineService.findbyID(id));
        if(machine.isPresent())
        {
            machineService.delete(id);
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getbyID(@PathVariable("id") Long id){
        Optional<Machine> machine = (machineService.findbyID(id));
        if(machine.isPresent()) {
            return ResponseEntity.ok(machine.get());
        }
        else return ResponseEntity.notFound().build();
    }


    @PutMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchMachine(@RequestBody SearchMachineRequest searchMachine){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName()).get();

        String email = user.getEmail();
        String machineName = searchMachine.getMachineName();
        List<Status> status = searchMachine.getStatus();
        LocalDate dateFrom = searchMachine.getDateFrom();
        LocalDate dateTo = searchMachine.getDateTo();

        System.out.println(dateTo);
        System.out.println(email);
        System.out.println(dateFrom);
        System.out.println(machineName);
        System.out.println(status);
        return new ResponseEntity<>(machineService.search(email,status,machineName,dateFrom,dateTo), HttpStatus.OK);
    }

    @PutMapping(value = "/start/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> Start(@PathVariable("id") Long id){
        return new ResponseEntity<>(machineService.start(id), HttpStatus.OK);
    }

    @PutMapping(value = "/stop/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> Stop(@PathVariable("id") Long id){
        return new ResponseEntity<>(machineService.stop(id), HttpStatus.OK);
    }

    @PutMapping(value = "/restart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> Restart(@PathVariable("id") Long id){
        return new ResponseEntity<>(machineService.restart(id), HttpStatus.OK);
    }

    @GetMapping(value = "/scheduleStart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ScheduleStart(@PathVariable("id") Long id, @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") Date date){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName()).get();

        return new ResponseEntity<>(machineService.scheduleStart(id,user.getUserId(),date), HttpStatus.OK);
    }

    @GetMapping(value = "/scheduleStop/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ScheduleStop(@PathVariable("id") Long id, @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") Date date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName()).get();

        return new ResponseEntity<>(machineService.scheduleStop(id,user.getUserId(),date), HttpStatus.OK);

    }

    @GetMapping(value = "/scheduleRestart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ScheduleRestart(@PathVariable("id") Long id, @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") Date date){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName()).get();

        return new ResponseEntity<>(machineService.scheduleRestart(id,user.getUserId(),date), HttpStatus.OK);
    }


}


