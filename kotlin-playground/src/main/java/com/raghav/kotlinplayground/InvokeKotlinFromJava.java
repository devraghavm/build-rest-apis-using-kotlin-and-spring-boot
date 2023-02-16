package com.raghav.kotlinplayground;

import com.kotlinplayground.classes.Authenticate;
import com.kotlinplayground.classes.Course;
import com.kotlinplayground.classes.CourseUtils;

public class InvokeKotlinFromJava {
    public static void main(String[] args) {
        var course = new Course(1,
                "Python 3",
                "Raghav"
//                ,
//                CourseCategory.DEVELOPMENT
        );
        course.noOfCourses = 11;

        System.out.println("course : " + course);
        System.out.println("noOfCourses : " + course.noOfCourses);
        CourseUtils.printName1();
        CourseUtils.printName1("abc");

        Course.Companion.printName2("abc");
        // After @JvmStatic Added
        Course.printName2("def");

        Authenticate.INSTANCE.authenticate("Raghav", "password");
        Authenticate.authenticate("Raghav", "password");
    }
}
