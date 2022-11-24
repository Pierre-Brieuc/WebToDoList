package com.example.webtodolist;

import javax.sql.DataSource;

public class InstructorDBUtil {
    private DataSource dataSource;
    public InstructorDBUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }
}
