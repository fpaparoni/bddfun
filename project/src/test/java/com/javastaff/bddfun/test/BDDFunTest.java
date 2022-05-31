package com.javastaff.bddfun.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.javastaff.bddfun.resource.AuthorResource;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BDDFunTest {
	
	@Mock
	private AuthorResource authorResource;
	
	@Test
	void mockReadyTest() {
		assertNotEquals(authorResource,null);
	}
}
