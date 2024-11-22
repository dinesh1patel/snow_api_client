package com.snowapi.client;

import com.snowapi.model.ResultTask;
import com.snowapi.model.ResultTaskVariable;
import com.snowapi.model.Task;
import com.snowapi.model.TaskVariable;
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

        ResultTask resultAllTasks = consumeWebService.getTasksAssignedToCFTL2();
		Task[] tasks = resultAllTasks.getResult();
		for (Task task : tasks) {
			System.out.println("Task ->" + task.getNumber());
			ResultTaskVariable variable = consumeWebService.getAllVariablesForRITMs(task.getRequest_item().getValue());
			for (TaskVariable taskVariable : variable.getResult()) {
				//System.out.println("RITM -> " + taskVariable.getNumber());
				//System.out.println("Request Type -> " +taskVariable.getVariables_request_type());
				//System.out.println("RITM sys_id -> " + taskVariable.getSys_id());
				//System.out.println(taskVariable.getVariables_ccd_account_profile_add();
				System.out.println(taskVariable);
			}
			System.out.println("-------------------------------------------------");
		}

		long endTime = System.currentTimeMillis();

		log.info("Completed process in " + (endTime - startTime) + " milliseconds");

		SpringApplication.exit(context, () -> 0);
	}
}
