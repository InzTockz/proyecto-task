package task.battilana.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling
public class TaskBattilanaApplication {

	public static void main(String[] args) {

		SpringApplication.run(TaskBattilanaApplication.class, args);
	}

}
