package dungeons_and_dragons.entity;
// Generated Jan 31, 2017 10:00:34 PM by Hibernate Tools 4.3.1



/**
 * Spells generated by hbm2java
 */
public class Spells  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String level;
     private String school;
     private String components;
     private String duration;
     private String class_;
     private String description;
     private String time;

    public Spells() {
    }

    public Spells(String name, String level, String school, String components, String duration, String class_, String description, String time) {
       this.name = name;
       this.level = level;
       this.school = school;
       this.components = components;
       this.duration = duration;
       this.class_ = class_;
       this.description = description;
       this.time = time;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getLevel() {
        return this.level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    public String getSchool() {
        return this.school;
    }
    
    public void setSchool(String school) {
        this.school = school;
    }
    public String getComponents() {
        return this.components;
    }
    
    public void setComponents(String components) {
        this.components = components;
    }
    public String getDuration() {
        return this.duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getClass_() {
        return this.class_;
    }
    
    public void setClass_(String class_) {
        this.class_ = class_;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }




}

