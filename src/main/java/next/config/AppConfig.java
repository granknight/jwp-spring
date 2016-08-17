package next.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Configuration
@ComponentScan(
        basePackages = {"next.service", "next.dao", "next.aspect"},
        excludeFilters = @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION)
)
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)//Enable Cglib proxy
public class AppConfig {

    //Logger log = LoggerFactory.getLogger(AppConfig.class);

//    private static final String DB_DRIVER = "org.h2.Driver";
//    private static final String DB_URL = "jdbc:h2:~/jwp-basic;AUTO_SERVER=TRUE";
//    private static final String DB_USERNAME = "sa";
//    private static final String DB_PW = "";

    @Value("${db.driver}")
    public String dbDriver;

    @Value("${db.url}")
    public String dbUrl;

    @Value("${db.username}")
    public String dbUserName;

    @Value("${db.password}")
    public String dbPassword;

//    @Value("${test}")
//    public String test;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
//        log.debug("test ::: {}",test);
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(dbDriver);
        ds.setUrl(dbUrl);
        ds.setUsername(dbUserName);
        ds.setPassword(dbPassword);
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
