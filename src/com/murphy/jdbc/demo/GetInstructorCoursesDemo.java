package com.murphy.jdbc.demo;

import com.murphy.jdbc.entity.Course;
import com.murphy.jdbc.entity.Instructor;
import com.murphy.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class GetInstructorCoursesDemo {

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

            // get courses from db
            int theInstructor = 1;
            Instructor tempInstructor = session.get(Instructor.class, theInstructor);

//            List<Course> courses = session.createQuery("FROM Course c WHERE c.instructor=1")
//                    .getResultList();
//
//            for(Course c : courses) {
//                System.out.println("\nCourse: "+ c);
//            }


            System.out.println("Instructor: " + tempInstructor);

            // get courses for instructor
            // Course class 記得加上 default constructor
            System.out.println("Courses: " + tempInstructor.getCourses());

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
