package com.jm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application implements CommandLineRunner {
       
  
        
     
	public static void main(String[] args) {
	    SpringApplicationBuilder builder=
	        new SpringApplicationBuilder(Application.class);
	    builder.banner((x,y,pr)->{
	    pr.println("============================================================");  
	    pr.println("=                                                          ="); 
	    pr.println("=     ADP-JPA-EMPLOYEE MicroService                        ="); 
	    pr.println("=                                                          =");  
	    pr.println("============================================================");                 
	                   });
	    builder.run(args);
	}

    @Override
    public void run(String[] args) throws Exception {
        
         log("============================================================");  
         log("=                                                          ="); 
         log("=     End of  Main Service                                 ="); 
         log("=     swagger: http://localhost:8080/swagger-ui.html       =");  
         log("=          H2: http://localhost:8080/h2-console            =");  
         log("=                                                          =");  
         log("=                                                          =");  
         log("============================================================"); 
     
    }
    
    private void log(String message){
        System.out.println(message);
    }
}
