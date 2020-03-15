package br.com.caelum;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
@EnableTransactionManagement
public class JpaConfigurator {

//	@Bean
//	public DataSource getDataSource() {
//	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//	    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//	    // porta do mysql
//	    dataSource.setUrl("jdbc:mysql://localhost:3306/projeto_jpa?useTimezone=true&serverTimezone=UTC");
//	    dataSource.setUsername("root");
//	    dataSource.setPassword("th@les1234");
//	    
//	    // ?useTimezone=true&serverTimezone=UTC");
//	    
//	    return dataSource;
//	}
	
	
	
	/*
	 * *
	 * Repare também que usamos o atributo destroyMethod na anotação @Bean.
	 * Esse atributo define o método (close) do Pool que o Spring chama quando o Tomcat é desligado.
	 *  Assim garantimos que todas as conexões serão fechadas corretamente.
	 * */	
	
	@Bean(destroyMethod = "close")
	public DataSource getDataSource() throws PropertyVetoException {
	    ComboPooledDataSource dataSource = new ComboPooledDataSource();

	    dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
	    dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/projeto_jpa?useTimezone=true&serverTimezone=UTC");
	    dataSource.setUser("root");
	    dataSource.setPassword("th@les1234");

	    // dataSource.setInitialPoolSize(5);// numero de conexões q seram criadas 
		dataSource.setMinPoolSize(3);// numero minimo de conexões q esperam o cliente, após esse numero o pool cria novas conexões
	    dataSource.setMaxPoolSize(5); // numero maximo de conexões q seram criadas 
	    dataSource.setNumHelperThreads(5);
	    
	    dataSource.setIdleConnectionTestPeriod(1); //a cada um segundo testamos as conexões ociosas
	    
	    
	    return dataSource;
	}
	
	
public Statistics statistics(EntityManagerFactory emf) {
	
	SessionFactory factory = emf.unwrap(SessionFactory.class);
	
	return factory.getStatistics();
} 
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactory.setPackagesToScan("br.com.caelum");
		entityManagerFactory.setDataSource(dataSource);

		entityManagerFactory
				.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "create-drop"); // "create-drop"
		 // habilitando cache de segundo nivel
	    props.setProperty("hibernate.cache.use_second_level_cache", "true");
	    props.setProperty("hibernate.cache.use_query_cache", "true"); // cache de query
	    props.setProperty("hibernate.generate_statistics", "true"); 
	    props.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory"); // provedor de cache
		entityManagerFactory.setJpaProperties(props);
		return entityManagerFactory;
	}

	@Bean
	public JpaTransactionManager getTransactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

}
