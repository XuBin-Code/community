package life.code.xubin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class XubinApplication {

    public static void main(String[] args) {

        SpringApplication.run(XubinApplication.class, args);
   System.out.println("启动成功");
    }

}
