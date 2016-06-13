package com.bartek.repositoryTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.bartek.repository.VisitRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup(value = "example-data.xml")
@DirtiesContext
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "example-data.xml" })
public class VisitRepositoryTest {

	@Autowired
	private VisitRepository visitRepository;
	

	@Test
	public void visitRepositoryShouldFindAllVisitsInYearAskedByUser () {
		
		assertEquals(3, visitRepository.findVisitsByYearOfVisit("2010").size());		
	}
	
	@Test
	public void visitRepositoryShouldFindVisitByYearAndMonthOfVisit(){
		
		assertEquals(2, visitRepository.findVisitsByYearAndMonthOfVisit("2010", "4").size());
	}

	
	
}
