package com.jm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application implements CommandLineRunner {
 @Value("###@@{server.port}")
 private int port;    
        
        Log  log=LogFactory.getLog(Application.class);
	public static void main(String[] args) {
	    SpringApplicationBuilder builder=
	        new SpringApplicationBuilder(Application.class);
	    builder.banner((x,y,pr)->{
	    pr.println("============================================================");  
	    pr.println("=                                                          ="); 
	    pr.println("${appTitle}"); 
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
        log("=     swagger: http://localhost:"+port+"/swagger-ui.html       =");  
        log("=          H2: http://localhost:"+port+"/h2-console            =");  
        log("=                                                          =");  
        log("=                                                          =");  
        log("============================================================"); 
     
    }
    
    private void log(String message){
        System.out.println(message);
    }
}
