package com.snowapi.client;

import com.snowapi.service.ConsumeWebService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {ApplicationParams.class, ConsumeWebService.class})
class ClientApplicationTests {

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

		String result = consumeWebService.uploadAttachment("c89862d51b065e50923afb25464bcbd0", "/Users/dineshpatel/Downloads/test.txt");
		assert result.contains("CREATED");
	}

}
