package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Pavel", "Katovich", "pavelK@mail.ru");
        Car car1 = new Car("Chevrolet", 444);


        User user2 = new User("Vova", "Albertovich", "VovaA@mail.ru");
        Car car2 = new Car("Shelby", 777);



        userService.addUser(user1.setUsersCar(car1).setUser(user1));
        userService.addUser(user2.setUsersCar(car2).setUser(user2));


        List<User> users = userService.getListUsers();
        for (User user : users) {
            System.out.println(user + " " + user.getUsersCar() + "\n");
        }


        /***System.out.println(userService.getFrom("Shelby", 77));
         Он тут не неходит пользователя с такой машиной, можно расскоментить
         и проверить
         */


        context.close();
    }
}
