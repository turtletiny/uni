
import java.util.ArrayList;

public class GradeProgram {

    public static void main(String[] args) {
        Student student = new Student();
        Menu menu = new Menu();
        int mainMenuChoice = menu.runMainMenu();
        
    }

}

class Menu {

    int runMainMenu() {
        while (true) {
            System.out.println("[1] Add assessments");
            System.out.println("[2] View assessments");
            System.out.println("[3] Modify grades");
            int mainMenuChoice = In.nextInt();
            if (mainMenuChoice >= 1 || mainMenuChoice <= 3) {
                return mainMenuChoice;
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    Assessment addAssessmentsMenu(Student student) {
        System.out.println("Enter assessment name:  ");
        String name = In.nextLine();
        System.out.println("Enter total possible marks: ");
        int total = In.nextInt();
        while (true) {
            System.out.println("Enter your mark: ");
            int mark = In.nextInt();
            if (mark > total) {
                System.out.println("Mark cannot be higher than total possible marks.");
            } else {
                return new Assessment(name, mark, total);
            }
        }
        
    }
}

enum Grade {
    HIGH_DISTINCTION, DISTINCTION, CREDIT, PASS, FAIL;

}

class Assessment {

    String name;
    int mark;
    int totalPossibleMarks;
    int fudgeMark;

    public Assessment(String name, int mark, int totalPossibleMarks) {
        this.name = name;
        this.mark = mark;
        this.totalPossibleMarks = totalPossibleMarks;
        this.fudgeMark = 0;
    }

    public Grade computeGrade() {
        double scoreAsPercentage = (double) this.mark / this.totalPossibleMarks * 100;
        if (scoreAsPercentage >= 85 && scoreAsPercentage <= 100) {
            return Grade.HIGH_DISTINCTION;
        } else if (scoreAsPercentage >= 75 && scoreAsPercentage < 85) {
            return Grade.DISTINCTION;
        } else if (scoreAsPercentage >= 65 && scoreAsPercentage < 75) {
            return Grade.CREDIT;
        } else if (scoreAsPercentage >= 50 && scoreAsPercentage < 65) {
            return Grade.PASS;
        } else if (scoreAsPercentage >= 0 && scoreAsPercentage < 50) {
            return Grade.FAIL;
        } else {
            System.out.println("Invalid");
            return null;
        }
    }

    public void penalise(int penalty) {
        if (penalty <= 0) {
            System.out.println("invalid");
            return;
        }
        this.fudgeMark = -1 * penalty;
        this.mark -= this.fudgeMark;
    }

    public void penalise(Grade newGrade) {
        if (newGrade == Grade.HIGH_DISTINCTION) {
            this.fudgeMark = this.totalPossibleMarks - this.mark;
        } else if (newGrade == Grade.DISTINCTION) {
            this.fudgeMark = this.mark - (int) (0.85 * (double) this.totalPossibleMarks);
        } else if (newGrade == Grade.CREDIT) {
            this.fudgeMark = this.mark - (int) (0.75 * (double) this.totalPossibleMarks);
        } else if (newGrade == Grade.PASS) {
            this.fudgeMark = this.mark - (int) (0.65 * (double) this.totalPossibleMarks);
        } else if (newGrade == Grade.FAIL) {
            this.fudgeMark = this.mark - (int) (0.5 * (double) this.totalPossibleMarks);
        }
        System.out.println("New fudgemark = " + this.fudgeMark);
        this.mark -= this.fudgeMark;
    }

    public String toString() {
        return "Assessment: " + this.name + "\nMark: " + this.mark + "/" + this.totalPossibleMarks + "\nGrade: "
                + computeGrade();
    }
}

class Student {

    ArrayList<Assessment> assessments;

    Student() {
        this.assessments = new ArrayList<>();
    }

    void addAssessment(String name, int mark, int totalPossibleMarks) {
        for (Assessment a : this.assessments) {
            if (a.name.equals(name)) {
                System.out.println("Assessment already exists");
                return;
            }
        }
        this.assessments.add(new Assessment(name, mark, totalPossibleMarks));
    }

    void addAssessment(String name, Grade grade) {
        for (Assessment a : this.assessments) {
            if (a.name.equals(name)) {
                System.out.println("Assessment already exists");
                return;
            }
        }
    }
}
