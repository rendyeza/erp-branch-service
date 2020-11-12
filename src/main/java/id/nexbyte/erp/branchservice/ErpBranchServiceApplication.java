package id.nexbyte.erp.branchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ErpBranchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpBranchServiceApplication.class, args);
    }

}
