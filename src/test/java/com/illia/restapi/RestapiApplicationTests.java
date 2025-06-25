package com.illia.restapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RestapiApplicationTests {



	@Test
	void contextLoads() {
	}

	@Test
	void concatTest() {

		String str1 = "Hello ";
		String str2 = "World";

		assertEquals("Hello World", str1 + str2);
	}


}
