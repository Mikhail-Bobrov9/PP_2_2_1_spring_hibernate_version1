package hiber;

import hiber.config.AppConfig;
//import hiber.model.Car;
import hiber.model.Car;
import hiber.model.User;
//import hiber.service.CarService;
import hiber.service.CarService;
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
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru"));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru"));

      Car car1 = new Car("BMW", 123);
      Car car2 = new Car("KIA", 12313);
      Car car3 = new Car("AUDI", 5346754);
      Car car4 = new Car("MB", 234);
      Car car5 = new Car("LADA", 679);
      Car car6 = new Car("DMC", 697579);

      car1.setUser(userService.listUsers().get(0));
      car2.setUser(userService.listUsers().get(1));
      car3.setUser(userService.listUsers().get(2));
      car4.setUser(userService.listUsers().get(3));
      car5.setUser(userService.listUsers().get(4));
      car6.setUser(userService.listUsers().get(5));

      carService.add(car1);
      carService.add(car2);
      carService.add(car3);
      carService.add(car4);
      carService.add(car5);
      carService.add(car6);


      List<User> users = userService.listUsers();
      for (User user2 : users) {
         System.out.println("Id = "+user2.getId());
         System.out.println("First Name = "+user2.getFirstName());
         System.out.println("Last Name = "+user2.getLastName());
         System.out.println("Email = "+user2.getEmail());
         System.out.println("Car = " + user2.getCar());
         System.out.println();

      }
      System.out.println(userService.getUserByModelAndSeries("MB", 234));

      context.close();
   }
}
