package ru.NSU.computerScience.GradeBook;

import java.util.*;

public class Gradebook {

    record SubjectInfo(String name, int semester) {}

    private int QualificationGrade;
    private final Map<SubjectInfo, Integer> Inf;
    private final Map<String, Integer> DiplomaApplication;
    private final Boolean[] Scholarship;

    public Gradebook() {
        Inf = new HashMap<>();
        DiplomaApplication = new HashMap<>();
        Scholarship = new Boolean[20];

    }

    public void addSubject(String SubjectName, int SubjectSemester) {
        Inf.put(new SubjectInfo(SubjectName, SubjectSemester), 0);
    }


    public void addSubjectsForSemester(int semester, String[] subjects) {
        if (subjects == null || subjects.length == 0) {
            throw new IllegalArgumentException();
        }
        for (String subject : subjects) {
            addSubject(subject, semester);
        }
    }

    public void grade(String SubjectName, int SubjectSemester, int grade) {
        Inf.put(new SubjectInfo(SubjectName, SubjectSemester), grade);
        DiplomaApplication.put(SubjectName, grade);
        if (Scholarship[SubjectSemester] == null) {
            Scholarship[SubjectSemester] = true;
        }
        if (grade != 5) {
            Scholarship[SubjectSemester] = false;
        }
    }

    public float averageGrade() {
        Collection<Integer> grades = Inf.values();
        int sum = 0;
        for (Integer grade : grades) {
            sum += grade;
        }
        int length = 0;
        for (Integer grade : grades) {
            if (grade != 0) {
                length += 1;
            }
        }
        return (float) sum / length;
    }

    public void setQualificationGrade(int grade) {
        QualificationGrade = grade;
    }

    public int getQualificationGrade() {
        return QualificationGrade;
    }

    public boolean isHonorDiploma() {
        Collection<Integer> grades = Inf.values();
        Collection<Integer> applicationGrades = DiplomaApplication.values();
        int sum = 0;
        for (Integer grade : grades) {
            sum += grade;
        }
        Boolean allGraterThan3 = true;
        for (Integer grade : grades) {
            if (grade <= 3) {
                allGraterThan3 = false;
                break;
            }
        }
        return allGraterThan3;
    }


    public boolean isScholarship (int semester){
        if (Scholarship[semester] == null){
            throw new IllegalArgumentException();
        }
        return Scholarship[semester];
    }

}
