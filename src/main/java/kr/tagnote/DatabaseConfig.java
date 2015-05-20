package kr.tagnote;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DatabaseConfig {
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();

		try {
			String username = "tagnote";
			String password = "1234";
			String dbUrl = "jdbc:postgresql://localhost:5432/tagnote";
			String DATABASE_URL = System.getenv("DATABASE_URL");
		
			if (DATABASE_URL != null) {
				URI dbUri = new URI(DATABASE_URL);
				
				username = dbUri.getUserInfo().split(":")[0];
				password = dbUri.getUserInfo().split(":")[1];
				dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
			}
			
			basicDataSource.setDriverClassName("org.postgresql.Driver");
			basicDataSource.setUrl(dbUrl);
			basicDataSource.setUsername(username);
			basicDataSource.setPassword(password);
			basicDataSource.setInitialSize(1);
		} catch (URISyntaxException e) {
			// Deal with errors here.
		}
		
		return basicDataSource;
	}
}
