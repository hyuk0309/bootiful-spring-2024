package org.example.bootifulspring2024;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
public class ModuleTest {

	@Test
	void moduleTest() {
		ApplicationModules am = ApplicationModules.of(ServiceApplication.class);
		am.verify();

		System.out.println(am);
		new Documenter(am).writeDocumentation();
	}

}
