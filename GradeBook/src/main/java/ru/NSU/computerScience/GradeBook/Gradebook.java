package ru.NSU.computerScience.GradeBook;

import java.util.*;

/**
 * Содержит оценки студента
 */
public class Gradebook {

    record SubjectInfo(String name, int semester) {
    }

    private int QualificationGrade;
    private final Map<SubjectInfo, Integer> Inf;
    private final Map<String, Integer> DiplomaApplication;
    private final Boolean[] Scholarship;

    /**
     * Структура зачётной книжки
     */
    public Gradebook() {
        Inf = new HashMap<>();
        DiplomaApplication = new HashMap<>();
        Scholarship = new Boolean[20];

    }

    /**
     * @param SubjectName     Переменная типа String, содержащая название предмета
     * @param SubjectSemester Переменная типа int, содержащая номер семестра
     *                        Добавляет предмет в семестр в зачётную книжку
     */
    public void addSubject(String SubjectName, int SubjectSemester) {
        Inf.put(new SubjectInfo(SubjectName, SubjectSemester), 0);
    }


    /**
     * @param semester Переменная типа int, содержащая номер семестра
     * @param subjects Массив типа String, содержащий предметы, относящиеся к семестру
     *                 Добавляет несколько предметов в указанный семестр в зачётную книжку
     */
    public void addSubjectsForSemester(int semester, String[] subjects) {
        if (subjects == null || subjects.length == 0) {
            throw new IllegalArgumentException();
        }
        for (String subject : subjects) {
            addSubject(subject, semester);
        }
    }

    /**
     * @param SubjectName     Переменная типа String, содержащая название предмета
     * @param SubjectSemester Переменная типа int, содержащая номер семестра для SubjectName
     * @param grade           Переменная типа int, содержащая оценку, необходимую для выставления в зачётную книжку
     *                        Добавляет оценку в зачётную книжку и в приложение к диплому
     */
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

    /**
     * @return средний балл за всё время обучения
     */
    public float averageGrade() {
        Collection<Integer> grades = Inf.values();
        Integer sum = grades.stream().reduce(0, Integer::sum);
        long length = grades.stream().filter(a -> a > 0).count();
        return (float) sum / length;
        /*int sum = 0;
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
         */
    }

    /**
     * @param grade Переменная типа int, которую необходимо выставить за дипломную работу
     *              Устанавливает оценку за диплом
     */
    public void setQualificationGrade(int grade) {
        QualificationGrade = grade;
    }

    /**
     * @return оценку за диплом
     */
    public int getQualificationGrade() {
        return QualificationGrade;
    }

    /**
     * @return True или False в зависимости от того, является ли диплом красным или нет
     */
    public boolean isHonorDiploma() {
        Collection<Integer> grades = Inf.values();
        Collection<Integer> DiplomaGrades = DiplomaApplication.values();
        long RedSum = DiplomaGrades.stream().reduce(0, Integer::sum);
        return grades.stream().allMatch(a -> a > 3)
                && (QualificationGrade == 5)
                && ((float) RedSum / DiplomaApplication.size() >= 4.75);
        /*
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
         */
    }


    /**
     * @param semester Переменная типа int, содержащая номер семестра
     * @return True или False в зависимости от того, есть ли у студента повышенная стипендия или нет
     */
    public boolean isScholarship(int semester) {
        if (Scholarship[semester] == null) {
            throw new IllegalArgumentException();
        }
        return Scholarship[semester];
    }

}
