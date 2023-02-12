package hiber.service;

import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    public User getFrom(String model, int series);

}
