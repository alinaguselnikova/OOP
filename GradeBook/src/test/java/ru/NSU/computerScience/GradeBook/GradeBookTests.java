package ru.NSU.computerScience.GradeBook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GradeBookTests {

        @Test
        public void testMyReGradeBook() {
            Gradebook book = new Gradebook();
            String[] semester1 = {"Mathematical analysis",
                    "Mathematical logic",
                    "Declarative programming",
                    "Imperative programming"};
            book.addSubjectsForSemester(1, semester1);
            book.grade("Mathematical analysis", 1, 4);
            book.grade("Mathematical logic", 1, 4);
            book.grade("Declarative programming", 1, 3);
            book.grade("Imperative programming", 1, 3);
            book.addSubject("Digital platforms", 1);
            assertFalse(book.isScholarship(1));
        }

        Gradebook book = new Gradebook();

        @Test
        public void testisScholarship(){
            Gradebook book = new Gradebook();
            book.grade("Russian language", 1, 5);
            book.grade("History", 1, 5);
            book.grade("Programming", 1, 5);
            Assertions.assertTrue(book.isScholarship(1));
        }

        @Test
        public void testHonorDiploma() {
            book.grade("Math", 1, 4);
            book.grade("History", 2, 5);
            book.grade("Russian Language", 2, 5);
            book.grade("Programming", 1, 5);
            book.grade("English", 1, 5);
            book.setQualificationGrade(5);
            Assertions.assertTrue(book.isHonorDiploma());
        }

}

