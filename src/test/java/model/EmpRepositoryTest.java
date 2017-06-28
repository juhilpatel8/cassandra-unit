package model;

import java.util.List;

import org.cassandraunit.CassandraCQLUnit;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import repository.EmpRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.matchers.JUnitMatchers.hasItem;

/**
 * @author lakshay13@gmail.com
 */
public class EmpRepositoryTest {

    @Rule
    public CassandraCQLUnit cassandraCQLUnit = new CassandraCQLUnit(new ClassPathCQLDataSet("cql/employee.cql", "emp_keyspace"));

    private EmpRepository empRepository;

    @Before
    public void setUp() throws Exception {
        empRepository = new EmpRepository(cassandraCQLUnit.session);
    }

    @Test
    public void testFindEmployeeById() throws Exception {
        EmpDetails empExpected = new EmpDetails(1, "Lakshay");

        EmpDetails empGenerated = empRepository.findEmployeeById(1);

        assertEquals(empExpected, empGenerated);
    }

    @Test
    public void testFindAllEmployees() throws Exception {

        // given
        EmpDetails expectedLakshay = new EmpDetails(1, "Lakshay");
        EmpDetails expectedGeorge = new EmpDetails(2, "George");
        EmpDetails expectedAndy = new EmpDetails(3, "Andy");
        EmpDetails expectedNicole = new EmpDetails(4, "Nicole");

        // generated
        List<EmpDetails> employeeGeneratedList = empRepository.findAllEmployees();

        // verify
        assertThat(employeeGeneratedList, hasItem(expectedLakshay));
        assertThat(employeeGeneratedList, hasItem(expectedGeorge));
        assertThat(employeeGeneratedList, hasItem(expectedAndy));
        assertThat(employeeGeneratedList, hasItem(expectedNicole));
    }

    @Test
    public void testInsertNewEmployee() throws Exception {

        EmpDetails empAdd = new EmpDetails(5, "Grecia");
        empRepository.insertDataIntoEmployees(empAdd);

        EmpDetails empGenerated = empRepository.findEmployeeById(5);

        assertEquals(empAdd, empGenerated);
    }
}
