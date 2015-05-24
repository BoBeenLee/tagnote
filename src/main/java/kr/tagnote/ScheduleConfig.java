package kr.tagnote;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kr.tagnote.util.HttpUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class ScheduleConfig {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleConfig.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static final String REQUEST_URL = "http://tagnote.herokuapp.com/test/get";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Scheduled(fixedRate=30000)
	public void repeatedRequest(){
		logger.info("result : " + HttpUtils.getJson(restTemplate, REQUEST_URL, null));
	}
}
