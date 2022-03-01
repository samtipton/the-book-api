package com.thebook.thebookapi.data.entity;


import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ScriptureEntity {
    @Id
    public int id;
    public String b;
    public String c;
    public String v;
    public String t;
}
