package com.example.studentanalytics;

public class marksdatamodel {

    public String _class;
     public String id;
    public String english;
     public String mathematics;
    public String punjabi;
     public String hindi;
     public String computer;
     public String history;
    public String geography;
     public String physics;
     public String chemistry;
     public String biology;


    marksdatamodel()
    {

    }


    marksdatamodel( String _class, String id, String english, String mathematics, String punjabi, String hindi, String computer, String history, String geography, String physics, String chemistry, String biology)
    {
        this._class =  _class;
        this.id =  id;
        this. english = english;
        this. mathematics = mathematics;
        this. punjabi = punjabi;
        this. hindi = hindi;
        this. computer = computer;
        this. history = history;
        this. geography = geography;
        this. physics = physics;
        this. chemistry = chemistry;
        this. biology= biology;
    }
}
