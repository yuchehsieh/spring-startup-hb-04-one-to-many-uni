package com.murphy.jdbc.demo;

import com.murphy.jdbc.entity.Course;
import com.murphy.jdbc.entity.Instructor;
import com.murphy.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CreateCoursesDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            // create some courses
            Course tempCourse1 = new Course("Chinese Speaking");
            Course tempCourse2 = new Course("Chinese Reading");
            Course tempCourse3 = new Course("Chinese Listening");
            Course tempCourse4 = new Course("Chinese Writing");

            // add courses to instructor
            tempInstructor.addCourse(tempCourse1);
            tempInstructor.addCourse(tempCourse2);
            tempInstructor.addCourse(tempCourse3);
            tempInstructor.addCourse(tempCourse4);

            // save the courses
            session.save(tempCourse1);
            session.save(tempCourse2);
            session.save(tempCourse3);
            session.save(tempCourse4);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {

            // add clean up code
            session.close();

            factory.close();
        }
    }
}
