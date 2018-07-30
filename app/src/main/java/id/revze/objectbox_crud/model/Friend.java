package id.revze.objectbox_crud.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Friend {
    @Id
    private long id;
    private String name;
    private String birthDate;
    private int gender;

    public Friend() {}

    public Friend(long id, String name, String birthDate, int gender) {
        setId(id);
        setName(name);
        setBirthDate(birthDate);
        setGender(gender);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
