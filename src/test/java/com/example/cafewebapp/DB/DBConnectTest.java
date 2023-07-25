package com.example.cafewebapp.DB;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectTest {
    @Test
    void checkDatabaseConnection(){
        Connection conn = DBConnect.getConn();
        assertInstanceOf(
                org.postgresql.jdbc.PgConnection.class, conn);
    }
}