package ru.knastnt.meshtest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
public class Profile extends AbstractBaseEntity {

    private String name;

    @Email // однако, пропускает email типа abc@def. Можно использовать @Pattern
    @Column(unique = true)  // Уникальная колонка в базе
    private String email;

//    @Range(min = 0, max = 130) //Можно поставить верхний порог
    @Min(0)
    private Integer age;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Чтобы JSON не парсился сюда. Да, в принципе и без этого created = new Date()
    @JsonFormat(shape = JsonFormat.Shape.NUMBER) //Unix epoch time in milliseconds
    private Date created = new Date();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", created=" + created +
                '}';
    }
}
