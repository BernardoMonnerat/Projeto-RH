package entities;

import java.util.Date;
import java.util.List;

public class funcionarios {

    private String name;
    private Integer id;


    public funcionarios(){

    }

    public funcionarios(String name, Integer id) {
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
