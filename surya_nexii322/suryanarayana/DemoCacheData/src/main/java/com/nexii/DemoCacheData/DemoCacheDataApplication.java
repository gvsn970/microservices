package com.nexii.DemoCacheData;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.nexii.DemoCacheData.repository.DemoRepository;

@EnableCaching
@SpringBootApplication
public class DemoCacheDataApplication implements CommandLineRunner{
  private static final Logger logger=LoggerFactory.getLogger(DemoCacheDataApplication.class);
	
	@Autowired
	private DemoRepository demoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoCacheDataApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		logger.info("......Fetching Person");
		logger.info("isbn-1234----->"+demoRepository.getDetailes("isbn-1234"));
		logger.info("isbn-4576----->"+demoRepository.getDetailes("isbn-576"));
		logger.info("isbn-1234----->"+demoRepository.getDetailes("isbn-1234"));
		logger.info("isbn-4567----->"+demoRepository.getDetailes("isbn-4567"));
		logger.info("isbn-1234----->"+demoRepository.getDetailes("isbn-1234"));
		logger.info("isbn-55555----->"+demoRepository.getDetailes("isbn-5555"));
		
	}
}
