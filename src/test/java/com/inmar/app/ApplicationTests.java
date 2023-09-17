package com.inmar.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
class ApplicationTests {
	@BeforeEach
	public void setup() {

	}
	@Test
	void contextLoads() {
	}
}
