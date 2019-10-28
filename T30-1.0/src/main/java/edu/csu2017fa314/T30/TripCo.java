package edu.csu2017fa314.T30;
import edu.csu2017fa314.T30.Controller.DataController;
import edu.csu2017fa314.T30.Controller.LayoutController;
import edu.csu2017fa314.T30.Controller.UserController;


import static spark.Spark.get;
import static spark.Spark.port;
import java.sql.SQLException;


public class TripCo
{

   private String name = "";

   public String getName()
   {
      return name;
   }

   public String getMessage()
   {
      if (name == "")
      {
         return "Hello!";
      }
      else
      {
         return "Hello " + name + "!";
      }
   }

   public void setName(String name)
   {
      this.name = name;
   }


   static int getHerokuAssignedPort() {
      ProcessBuilder processBuilder = new ProcessBuilder();
      if (processBuilder.environment().get("PORT") != null) {
         return Integer.parseInt(processBuilder.environment().get("PORT"));
      }
      return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
   }


   public static void main(String[] args) throws SQLException {

      //configure port
      port(getHerokuAssignedPort());
      get("/hello", (req, res) -> "Hello Heroku World");

      new LayoutController();
      new UserController();
   }


}
