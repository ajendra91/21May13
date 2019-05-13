package com.example.RestListEnhance;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    public int id;
    public String name;
    public Date dateOfBirth;

    public void setId(int id) {
        this.id = id;
    }
}
