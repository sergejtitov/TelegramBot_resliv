package com.resliv.stitov;

import com.resliv.stitov.configs.DatabaseConfig;
import com.resliv.stitov.configs.JdbcTemplateConfig;
import com.resliv.stitov.configs.SwaggerConfig;
import com.resliv.stitov.configs.TelegramConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.telegram.telegrambots.ApiContextInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.Properties;

@EnableSwagger2
@EnableAspectJAutoProxy
@EnableJpaRepositories
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = "com.resliv.stitov")
@Import({DatabaseConfig.class,
        JdbcTemplateConfig.class,
        SwaggerConfig.class,
        TelegramConfig.class})
public class ApplicationStarter extends SpringBootServletInitializer {
    public static void main(String[] args)
    {
        ApiContextInitializer.init();
        SpringApplication.run(ApplicationStarter.class, args);
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.resliv.stitov");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getAdditionalProperties());

        return em;
    }

    private Properties getAdditionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.archive.autodetection", "class, hbm");
        properties.put("current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }
}
