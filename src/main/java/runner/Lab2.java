package runner;

import entity.Group;
import entity.Student;
import utils.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class Lab2 {

    public static void main(String[] args) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        //Update
        //Разделить группу, численностью более 25 человек на 2 отдельные группы
        List<Group> groups = session.createQuery("FROM Group").list();
        int stud = 0;

        for (Group group : groups) {
            System.out.println(group.getStudents().size());
            if (group.getStudents().size() > 25)
            {
                Group group1 = new Group(group.getName() + "(1)", group.getCreateDate(), group.getPlanCode(), group.getStatus(), new Date());
                Group group2 = new Group(group.getName() + "(2)", group.getCreateDate(), group.getPlanCode(), group.getStatus(), new Date());
                session.save(group1);
                session.save(group2);

                for (Student student : group.getStudents()) {
                    if (stud < 15) {
                        student.setGroup(group1);
                        stud++;
                    }
                    else student.setGroup(group2);
                    session.update(student);
                }
                stud = 0;
            }
        }

        session.flush();
        transaction.commit();
        session.close();
        sf.close();
    }
}
