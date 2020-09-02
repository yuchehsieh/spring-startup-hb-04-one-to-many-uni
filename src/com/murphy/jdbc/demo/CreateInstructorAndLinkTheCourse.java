package com.murphy.jdbc.demo;

import com.murphy.jdbc.entity.Course;
import com.murphy.jdbc.entity.Instructor;
import com.murphy.jdbc.entity.InstructorDetail;
import com.murphy.jdbc.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorAndLinkTheCourse {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .addAnnotatedClass(Review.class)
                                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // create an instructor
            Instructor tempInstructor = new Instructor("Murphy", "Hsieh", "murphy@luv2code.com");

            // create instructor detail
            InstructorDetail tempInstructorDetail = new InstructorDetail("https://youtube.com/luv2code", "watching youtube!");

            // get the course we just created
            int theId = 10;
            Course theCourse = session.get(Course.class, theId);

            // add the course to the instructor
            tempInstructor.addCourse(theCourse);

            // add the instructor detail
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // save the instructor
            session.save(tempInstructor);

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
