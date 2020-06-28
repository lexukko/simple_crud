package com.example.simplecrud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.FileCopyUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class DataSourceConfig {

    @Value("${spring.datasource.platform:}")
    private String platform = null;
    @Value("${spring.datasource.url:}")
    private String url = null;
    @Value("${spring.datasource.username:}")
    private String username = null;
    @Value("${spring.datasource.password:}")
    private String password = null;
    @Value("${spring.datasource.password_file:}")
    private String password_file = null;


    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        if(password!=null && !password.isEmpty()) {
            System.out.println(password);
            dataSourceBuilder.password(password);
        }else if(password_file!=null && !password_file.isEmpty()){
            try {
                dataSourceBuilder.password(getContents(password_file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataSourceBuilder.build();
    }

    private String getContents(String file) throws IOException {
        File f = new File(file);
        String res = new String(FileCopyUtils.copyToByteArray(f));
        res = res.replace("\n","");
        return res;
    }

}
