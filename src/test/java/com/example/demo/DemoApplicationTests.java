package com.example.demo;

import com.example.demo.dao.mapper.TeachingOutlineMapper;
import com.example.demo.dao.pojo.TeachingOutline;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private TeachingOutlineMapper mapper;

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<TeachingOutline> userList = mapper.selectList(null);
		userList.forEach(System.out::println);
	}

}
