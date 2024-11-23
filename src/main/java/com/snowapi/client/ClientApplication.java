package com.snowapi.client;

import com.snowapi.model.ResultTask;
import com.snowapi.model.ResultTaskVariable;
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
		ResultTask.Task[] tasks = resultAllTasks.getResult();
		for (ResultTask.Task task : tasks) {
			System.out.println("Task Number ->" + task.getNumber());
			System.out.println("Task SYS_ID ->" + task.getSys_id());
			ResultTaskVariable variable = consumeWebService.getAllVariablesForRITMs(task.getRequest_item().getValue());
			for (ResultTaskVariable.TaskVariable taskVariable : variable.getResult()) {
				System.out.println("RITM -> " + taskVariable.getNumber());
				System.out.println("Request Type -> " +taskVariable.getVariables_request_type());
				System.out.println("RITM sys_id -> " + taskVariable.getSys_id());
				System.out.println("Group to add -> " + taskVariable.getVariables_ccd_account_profile_add());
				System.out.println("RITM Variables -> " + taskVariable);

				System.out.println("Doing the IDAM stuff, i.e. add, remove etc.");
				System.out.println("Generating output of IDAM action");
				System.out.println("Created output file: /Users/dineshpatel/Downloads/test.txt");

				/*
				System.out.println("Uploading output file to ServiceNow");

                String result = null;
                try {
                    result = consumeWebService.uploadAttachment(task.getSys_id(), "/Users/dineshpatel/Downloads/test.txt");
					System.out.println("Result of upload: " + result);
				} catch (Exception e) {
                    throw new RuntimeException(e);
                }
				*/
			}
			System.out.println("-------------------------------------------------");
		}

		long endTime = System.currentTimeMillis();

		log.info("Completed process in " + (endTime - startTime) + " milliseconds");

		SpringApplication.exit(context, () -> 0);
	}
}
