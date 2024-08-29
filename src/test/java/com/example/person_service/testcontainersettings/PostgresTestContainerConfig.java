package com.example.person_service.testcontainersettings;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class PostgresTestContainerConfig {

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgreSQLContainer(){
        return new PostgreSQLContainer<>("postgres:latest");
    }
}


//
//@TestConfiguration(proxyBeanMethods = false)
//public class PostgresTestContainerConfig {
//
//    @Container
//    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;
//
//    static {
//        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:latest")
//                .withUsername("postgres")
//                .withPassword("postgres")
//                .withDatabaseName("person_testcontainer");
//    }
//
//    @DynamicPropertySource
//    public static void dynamicPropertySource(DynamicPropertyRegistry registry){
//        registry.add("spring.flyway.url",POSTGRE_SQL_CONTAINER::getJdbcUrl);
//        registry.add("spring.flyway.name",POSTGRE_SQL_CONTAINER::getUsername);
//        registry.add("spring.flyway.password",POSTGRE_SQL_CONTAINER::getPassword);


//    String url = "r2dbc:postgres:" + POSTGRE_SQL_CONTAINER.getJdbcUrl().substring(16);
//        System.setProperty("DB_URL", url);
//    }
//}
