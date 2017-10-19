package br.ufg.pos.fswm.pba.emprestimos;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({"classpath:application-test.properties", "classpath:emprestimo-api-test.properties"})
public abstract class EmprestimosApplicationTests {

	protected static final String HEADER_ACCEPT = "Accept";
	protected static final String HEADER_CONTENT_TYPE = "Content-type";

	@Value("${local.server.port}")
	protected int porta;

	@Before
	public void setUp() throws Exception {
		RestAssured.port = porta;
	}

}
