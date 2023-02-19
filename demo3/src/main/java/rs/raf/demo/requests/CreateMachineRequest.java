package rs.raf.demo.requests;

import javax.validation.constraints.NotBlank;

public class CreateMachineRequest{

    @NotBlank(message = "name is mandatory.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
