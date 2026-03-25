package com.lelegspears.simple_world_chat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
class SimpleWorldChatApplicationTests {

	@Test
	@Profile(value ="test")
	void contextLoads() {
	}

}
