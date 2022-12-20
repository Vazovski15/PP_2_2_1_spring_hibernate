package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User carToUser(Car car){
      Query query=sessionFactory.getCurrentSession().createQuery("FROM User as user WHERE user.carUser.model=:model " +
              "and  user.carUser.series=:series");
      query.setParameter("model",car.getModel()).setParameter("series",car.getSeries());

      return (User) query.getSingleResult();

   }



}
