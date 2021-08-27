package be.iccbxl.pid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // ANNOTATION CHE INCLUDE MOLTE ALTRE ANNOTATION , like enable ecc ecc
public class ProjectIntegrationAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProjectIntegrationAppApplication.class, args);
    }
    //select role_id, user_id from roles r, users u,role_user ro where r.id = ro.role_id ,u.id = ro.user_id and u.login =?

}
