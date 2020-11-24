package com.alliander.service;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;

import com.alliander.database.BaseXClient;
import com.alliander.database.BaseXClient.Query;

@ApplicationScoped
public class BaseXService {

    /**
     * Execute a command in BaseX
     * @param command the command to execute
     * @return the result
     * @throws IOException
     */
    public String executeComand(String command) throws IOException {
        try (BaseXClient client = new BaseXClient("localhost", 1984, "admin", "admin")) {
            return client.execute(command);
        }
    }

    /**
     * Execute a query in BaseX
     * @param database the database to execute the query on
     * @param query the query to execute
     * @return the result
     * @throws IOException
     */
    public String executeQuery(String database, String query) throws IOException {
        try (BaseXClient client = new BaseXClient("localhost", 1984, "admin", "admin")) {
            String response = "";
            client.execute("open ".concat(database));
            try (Query queryToRun = client.query(query)) {
                while(queryToRun.more()) {
                    response += queryToRun.next();
                }
            }
            client.execute("close");
            return response;
        }
    }

}