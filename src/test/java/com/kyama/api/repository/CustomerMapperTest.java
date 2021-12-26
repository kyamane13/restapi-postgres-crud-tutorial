package com.kyama.api.repository;

import com.kyama.api.config.DbConfig;
import com.kyama.api.dto.Customer;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Import(DbConfig.class)
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper mapper;
    @Autowired
    private DataSource ds;

    private IDatabaseConnection dbconn;

    private IDataSet inputCsvDataSet;

    @BeforeEach
    public void setup() throws Exception {
        this.dbconn = new DatabaseConnection(this.ds.getConnection());
        this.inputCsvDataSet = new CsvDataSet(new File("src/test/resource/com/kyama/api/repository"));
        DatabaseOperation.CLEAN_INSERT.execute(dbconn, inputCsvDataSet);
    }

    @AfterEach
    public void teardown() throws Exception {
        this.dbconn.close();
    }

    @DisplayName("INSERT TEST: Check if the data is inserted as expected.")
    @Test
    public void testInsert() {
        Customer customer = new Customer();
        customer.setId("100");
        customer.setUsername("user100");
        customer.setEmail("test.user.100@example.com");
        customer.setPhoneNumber("01234567890");
        customer.setPostCode("1234567");

        assertEquals(1, mapper.insert(customer));
    }
}
