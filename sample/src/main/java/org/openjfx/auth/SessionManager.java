package org.openjfx.auth;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.openjfx.model.User;
import org.openjfx.utils.HibernateUtil;






public class SessionManager {
    private static User currentUser;




    public static boolean authenticateUser(String username, String password) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
           

        try {
            session.beginTransaction();

            Query<User> query = session.createQuery(
                    "FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);

            User user = query.uniqueResult();

            SessionManager.setCurrentUser(user); 
            session.getTransaction().commit();


            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            return false;
        } 
    }





    /**
     * Set the current user that is logged in.
     *
     * @param user The user that is currently logged in.
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Get the current user that is logged in.
     *
     * @return The current user if one is set, otherwise <code>null</code>.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Get the current user's role.
     * @return The role of the current user if one is set, otherwise <code>null</code>.
     */
    public static String getCurrentUserRole() {
        return (currentUser != null) ? currentUser.getRole() : null;
    }

    /**
     * Clear the current user session.
     */
    public static void clearSession() {
        currentUser = null;
    }
}