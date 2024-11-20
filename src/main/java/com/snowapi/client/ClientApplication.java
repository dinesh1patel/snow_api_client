package com.snowapi.client;

import com.snowapi.model.Result;
import com.snowapi.model.Task;
import com.snowapi.service.ConsumeWebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication()
@Slf4j
public class ClientApplication implements CommandLineRunner {

	@Autowired
	private ApplicationParams applicationParams;

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) {

		long startTime = System.currentTimeMillis();
		log.info("Starting process");

		ConsumeWebService consumeWebService = new ConsumeWebService(
				applicationParams.getSnowUsername(),
				applicationParams.getSnowPassword()
		);

		Result resultAllTasks = consumeWebService.getTasksAssignedToCFTL2();
		Task[] tasks = resultAllTasks.getResult();
		for (Task task : tasks) {
			System.out.println(task.getNumber());
			//System.out.println(task.getRequest().getLink());
			//System.out.println(task.getRequest().getValue());
			System.out.println(task.getRequest_item().getLink());
			System.out.println(task.getRequest_item().getValue());
		}

		long endTime = System.currentTimeMillis();

		log.info("Completed process in " + (endTime - startTime) + " milliseconds");

		SpringApplication.exit(context, () -> 0);
	}
}
