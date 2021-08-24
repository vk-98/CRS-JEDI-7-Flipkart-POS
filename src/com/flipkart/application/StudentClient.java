package com.flipkart.application;

import com.flipkart.business.CourseInterface;
import com.flipkart.business.CourseInterfaceImpl;
import com.flipkart.business.UserInterfaceImpl;

import java.util.Scanner;

public class StudentClient {
    Scanner sc = new Scanner(System.in);
    CourseInterface courseInterface = new CourseInterfaceImpl(10);
    public void showMenu() {

        while (true) {
            showMenuItems();
            int userInput = sc.nextInt();

            if(userInput==4) {
                UserInterfaceImpl.logout();
                break;
            }

            switch (userInput) {
                case 1:
                    System.out.println("View Courses");
                    courseInterface.viewCourses();
                    break;
                case 2:
                    System.out.println("semester");
                    break;
                case 3:
                    System.out.println("Pay fee");
                    break;
                default:
                    System.out.println("Invalid User Input");
            }
        }
    }
    static void showMenuItems() {
        System.out.println("1. View Courses");
        System.out.println("2. Semester Registration");
        System.out.println("3. Pay Fee");
        System.out.println("4. Logout");
    }
}
