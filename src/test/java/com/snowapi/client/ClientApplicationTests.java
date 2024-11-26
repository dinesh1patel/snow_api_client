package com.snowapi.client;

import com.snowapi.enums.State;
import com.snowapi.service.ConsumeWebService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {ApplicationParams.class, ConsumeWebService.class})
class ClientApplicationTests {

	private static final String TASK_SYS_ID = "c89862d51b065e50923afb25464bcbd0";

	@Autowired
	private ApplicationParams applicationParams;

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
	}

	@Test
	void uploadFileToIncidentReturnsSuccess() throws Exception {
		ConsumeWebService consumeWebService = new ConsumeWebService(
				applicationParams.getSnowUsername(),
				applicationParams.getSnowPassword(),
				applicationParams.getSnowApiUrl()
		);

		String result = consumeWebService.uploadAttachment(TASK_SYS_ID, "/Users/dineshpatel/Downloads/test.txt");
		assert result.contains("CREATED");
	}

	@Test
	void closeTaskAsCompletedReturnsSuccess() throws Exception {
		ConsumeWebService consumeWebService = new ConsumeWebService(
				applicationParams.getSnowUsername(),
				applicationParams.getSnowPassword(),
				applicationParams.getSnowApiUrl()
		);

		ResponseEntity<String> result = consumeWebService.updateTaskState(TASK_SYS_ID, State.CLOSED_COMPLETE);
		assert result.getStatusCode().is2xxSuccessful();
	}

}
