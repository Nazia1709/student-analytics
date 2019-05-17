package com.example.studentanalytics;

public class addstudentmodel {
    public   String name;
    public   String class1;
    public   String id;
    public   String parentname;
    public   String parentcontact;
    public   String parentemailid;

    public addstudentmodel()
    {

    }

    public addstudentmodel(String name, String class1, String id,String parentname, String parentcontact, String parentemailid)
    {
        this.name = name;
        this.class1 = class1;
        this.id = id;
        this.parentname = parentname;
        this.parentcontact = parentcontact;
        this.parentemailid = parentemailid;
    }
}
