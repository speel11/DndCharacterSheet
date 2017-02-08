package dungeons_and_dragons.util;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Sean Peel
 */
public class DatabaseQuery {
    
    public List executeQuery(String hql) {
        return executeHQLQuery(hql);
    }

    private List executeHQLQuery(String hql) {
        
        List resultList = null;
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(hql);
            resultList = q.list();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            throw new HibernateException("Hibernate Error. Cannot execute query: " + hql);
        }
        
        return resultList;
    }
    
    public Object executeUserQuery(String user) {
        return executeUserHQLQuery(user);
    }
    
    private Object executeUserHQLQuery(String user) {
        Object result = null;
        String usernameQuery = "FROM Users u where u.username='" + user + "'";
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(usernameQuery);
            result = q.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            throw new HibernateException("Hibernate Error. Cannot execute query: " + usernameQuery);
        }
        
        return result;
    }
}
