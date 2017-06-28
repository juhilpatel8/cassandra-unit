package repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import model.EmpDetails;

/**
 * @author lakshay13@gmail.com
 */
public class EmpRepository {

    private Session session;

    public EmpRepository(Session session) {
        this.session = session;
    }

    public EmpDetails findEmployeeById(int id) {
        EmpDetails empDetails = null;

        ResultSet resultSet = session.execute("select * from employee where id="+ id);
        for (Row row: resultSet) {
             empDetails = new EmpDetails(row.getInt("id"), row.getString("name"));
        }
        return empDetails;
    }

    public List<EmpDetails> findAllEmployees() {
        List<EmpDetails> empDetailsList = new ArrayList<EmpDetails>();

        ResultSet resultSet = session.execute("select * from employee");
        for (Row row: resultSet) {
            empDetailsList.add(new EmpDetails(row.getInt("id"), row.getString("name")));
        }
        return empDetailsList;
    }

    public void insertDataIntoEmployees(EmpDetails empDetails) {

        session.execute("INSERT INTO employee (id, name) VALUES (?, ?)", empDetails.getId(), empDetails.getName());
    }
}
