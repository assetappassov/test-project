package kz.java.core.annotation;

@ControlledObject(name = "biscuits")
public class Cookies {    
    
     @StartObject
     public void createCookie(){
       //бизнес логика
     }    
     @StopObject
     public void stopCookie(){
       //бизнес логика
     }
}