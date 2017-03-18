package pl.altkom.shop;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
@EnableTransactionManagement(proxyTargetClass = true)
public class DBConfig {

	@Value("${db.driverClassName}")
	private String driverClassName;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		// DriverManagerDataSource dataSource = new DriverManagerDataSource();
		// dataSource.setDriverClassName(driverClassName);
		// dataSource.setUrl(url);
		// dataSource.setUsername(username);
		// dataSource.setPassword(password);

		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		DataSource dataSource = dsLookup.getDataSource("java:jboss/datasources/products");
		return dataSource;

	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
		emFactory.setPersistenceUnitName("products");

		Properties properties = new Properties();
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		// properties.put("hibernate.transaction.flush_before_completion",
		// true);
		// properties.put("hibernate.transaction.auto_close_session", true);
		// properties.put("hibernate.transaction.coordinator_class", "jta");
		// properties.put("hibernate.current_session_context_class", "jta");
		// properties.put("hibernate.transaction.jta.platform", "JBossAS");
		emFactory.setJpaProperties(properties);
		emFactory.setDataSource(dataSource());
		emFactory.setPackagesToScan(new String[] { "pl.altkom.shop.model" });
		emFactory.setJpaVendorAdapter(createHibernateAdapter());
		return emFactory;
	}

	private HibernateJpaVendorAdapter createHibernateAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	@Autowired
	public JpaTransactionManager transactionManager(EntityManagerFactory em) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(em);
		return txManager;
	}

	// @Bean
	// @Autowired
	// public JtaTransactionManager transactionManager(EntityManagerFactory em)
	// {
	// JtaTransactionManager txManager = new JtaTransactionManager();
	// return txManager;
	// }
}
