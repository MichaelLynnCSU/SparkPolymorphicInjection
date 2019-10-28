package edu.csu2017fa314.T30.Controller;

import com.google.gson.Gson;

import edu.csu2017fa314.T30.Model.Users.BaseService;
import edu.csu2017fa314.T30.Model.Users.Guest.Guest;
import edu.csu2017fa314.T30.Model.Users.Guest.GuestService;
import edu.csu2017fa314.T30.Model.Users.User.User;
import edu.csu2017fa314.T30.Model.Users.User.UserService;
import org.apache.velocity.app.VelocityEngine;


import java.sql.SQLException;
import java.util.Properties;
import static spark.Spark.*;



public class UserController {
    VelocityEngine ve;
    Properties props;
    Gson gson;
    int test = 0;
    BaseService myService;



    public UserController() {


        ve = new VelocityEngine();
        props = new Properties();
        props.put("file.resource.loader.path", "C:/Users/aplus/Documents/GitHub/JavaRestSP1/T30-1.0/src/main/java/edu/csu2017fa314/T30/View/");
        ve.init(props);
        gson = new Gson();

        post("/usersearch", (request, response) ->{
            // user bean equivalent
            myService = new UserService();

            response.status(200);
            response.type("application/json");
            User user = new Gson().fromJson(request.body(), User.class);
            String json = myService.search("email1");
            System.out.println(json);
            return json;
        });

        post("/addUsers", (request, response) ->{
            response.status(200);
            // user bean equivalent
            myService = new UserService();
            User user = new Gson().fromJson(request.body(), User.class);

            User u1  = new User("1","Mike", "Lynn", "email1");
            User u2  = new User("2","Mike", "Lynn", "email2");
            User u3  = new User("3","Mike", "Lynn", "email3");


            myService.addUser(u1);
            myService.addUser(u2);
            myService.addUser(u3);

            // fiddler
            //    {
            //      "id": "John",
            //      "firstName": "Enginner",
            //      "lastName": "India",
            //      "email": "test"
            //    }

            String json = myService.getAllData();
            return json;
        });

        post("/addGuests", (request, response) ->{
            response.status(200);
            User user = new Gson().fromJson(request.body(), User.class);
            // guest bean equivalent
            myService = new GuestService();
            Guest g1  = new Guest("1","Mike", "Lynn", "email1");
            Guest g2  = new Guest("2","Mike", "Lynn", "email2");
            Guest g3  = new Guest("3","Mike", "Lynn", "email3");

            myService.addUser(g1);
            myService.addUser(g2);
            myService.addUser(g3);

            // fiddler
            //    {
            //      "id": "John",
            //      "firstName": "Enginner",
            //      "lastName": "India",
            //      "email": "test"
            //    }
            String json = myService.getAllData();
            return json;
        });

}
}