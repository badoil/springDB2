package hello.itemservice;

import hello.itemservice.config.*;
import hello.itemservice.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.UUID;


//@Import(MemoryConfig.class)
//@Import(JdbcTemplateConfigV1.class)
//@Import(JdbcTemplateConfigV2.class)
//@Import(JdbcTemplateConfigV3.class)
//@Import(MyBatisConfig.class)
//@Import(JpaConfig.class)
//@Import(SpringDataJpaConfig.class)
//@Import(QueryDslConfig.class)
@Import(V2Config.class)
//@SpringBootApplication(scanBasePackages = "hello.itemservice.web")
@SpringBootApplication(scanBasePackages = "hello.itemservice.datajpa")
@Slf4j
@EnableJpaAuditing
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	@Bean
	@Profile("local")
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		return new TestDataInit(itemRepository);
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		return () -> Optional.of(UUID.randomUUID().toString());
	}

//	@Bean
//	@Profile("test")
//	public DataSource dataSource() {
//		log.info("메모리 데이터베이스 초기화");
//		DriverManagerDataSource dataSource = new DriverManagerDataSource(); dataSource.setDriverClassName("org.h2.Driver"); dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1"); dataSource.setUsername("sa");
//		dataSource.setPassword("");
//		return dataSource;
//	}
}
