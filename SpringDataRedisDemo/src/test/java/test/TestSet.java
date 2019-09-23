package test;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class TestSet {
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void setValue() {
		redisTemplate.boundSetOps("nameSet").add("曹操");
		redisTemplate.boundSetOps("nameSet").add("刘备");
		redisTemplate.boundSetOps("nameSet").add("曹操");
	}
	
	@Test
	public void getValue() {
		Set members = redisTemplate.boundSetOps("nameSet").members();
		System.out.println(members);
	}
	
	@Test
	public void removeValue() {
		redisTemplate.boundSetOps("nameSet").remove("刘备");
	}
	
	@Test
	public void delete() {
		redisTemplate.delete("nameSet");
	}
}
