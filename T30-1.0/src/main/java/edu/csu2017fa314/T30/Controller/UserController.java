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

    // beans that decouples endpoints
    BaseService myUserService = new UserService();
    BaseService myGuestService = new GuestService();


    public UserController() {


        ve = new VelocityEngine();
        props = new Properties();
        props.put("file.resource.loader.path", "C:/Users/aplus/Documents/GitHub/JavaRestSP1/T30-1.0/src/main/java/edu/csu2017fa314/T30/View/");
        ve.init(props);
        gson = new Gson();

        post("/usersearch", (request, response) ->{
            // user bean equivalent

            response.status(200);
            response.type("application/json");
            User user = new Gson().fromJson(request.body(), User.class);
            String json = myUserService.search("email1");
            System.out.println(json);
            return json;
        });

        post("/addUsers", (request, response) ->{
            response.status(200);

            User user = new Gson().fromJson(request.body(), User.class);

            User u1  = new User("1","Mike", "Lynn", "email1");
            User u2  = new User("2","Mike", "Lynn", "email2");
            User u3  = new User("3","Mike", "Lynn", "email3");


            myUserService.addUser(u1);
            myUserService.addUser(u2);
            myUserService.addUser(u3);

            // fiddler
            //    {
            //      "id": "John",
            //      "firstName": "Enginner",
            //      "lastName": "India",
            //      "email": "test"
            //    }

            String json = myUserService.getAllData();
            return json;
        });

        post("/addGuests", (request, response) ->{
            response.status(200);
            User user = new Gson().fromJson(request.body(), User.class);

            Guest g1  = new Guest("1","Mike", "Lynn", "email1");
            Guest g2  = new Guest("2","Mike", "Lynn", "email2");
            Guest g3  = new Guest("3","Mike", "Lynn", "email3");

            myGuestService.addUser(g1);
            myGuestService.addUser(g2);
            myGuestService.addUser(g3);

            // fiddler
            //    {
            //      "id": "John",
            //      "firstName": "Enginner",
            //      "lastName": "India",
            //      "email": "test"
            //    }
            String json = myGuestService.getAllData();
            return json;
        });

}
}