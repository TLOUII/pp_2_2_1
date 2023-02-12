package hiber.dao;

import hiber.model.User;
import hiber.service.UserServiceImp;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserDaoImp implements UserDao {
    private final Logger logger = Logger.getLogger(UserDaoImp.class.getName());
    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getFrom(String model, int series) {
        TypedQuery<User> query = null;
        try {
            query = sessionFactory.getCurrentSession().
                    createQuery("from User user where user.usersCar.model = :model and user.usersCar.series = :series");

            query.setParameter("model", model).setParameter("series", series);
            return query.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            logger.info("Пользователь с таким автомобилем не найден!");
            return null;
        }
    }

}
