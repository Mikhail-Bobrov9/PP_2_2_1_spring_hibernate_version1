package hiber;

import hiber.config.AppConfig;
//import hiber.model.Car;
import hiber.model.Car;
import hiber.model.User;
//import hiber.service.CarService;
import hiber.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car1 = new Car("BMW", 123);
      Car car2 = new Car("KIA", 254);
      Car car3 = new Car("AUDI", 376);
      Car car4 = new Car("MB", 984);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("LADA",911)));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("HAVAL",543)));
      User user = new User("User7", "Lastname7", "user7@mail.ru", new Car("MINI",243));
      userService.add(user);

      List<User> users = userService.listUsers();
      for (User user2 : users) {
         System.out.println("Id = "+user2.getId());
         System.out.println("First Name = "+user2.getFirstName());
         System.out.println("Last Name = "+user2.getLastName());
         System.out.println("Email = "+user2.getEmail());
         System.out.println("Car = " + user2.getCar());
         System.out.println();

      }
      System.out.println(userService.getUserByModelAndSeries("MB", 984));

      context.close();
   }
}
