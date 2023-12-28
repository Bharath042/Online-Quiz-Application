import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents a single question in the quiz
class Question {
    private String text;
    private List<String> options;
    private int correctOptionIndex;

    // Constructor for creating a question
    public Question(String text, List<String> options, int correctOptionIndex) {
        this.text = text;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    // Getter methods for accessing the question details
    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

// Represents the quiz and handles the quiz-taking logic
class Quiz {
    private List<Question> questions;
    private int countRight;
    private int countWrong;
    int countTotal;

    // Constructor for creating a quiz
    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.countRight = 0;
        this.countWrong = 0;
        this.countTotal = 0;
    }

    // Method to take the quiz
    public void takeQuiz() {
        for (Question question : questions) {
            // Display the question text
            System.out.println(question.getText());

            // Display the answer options
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println(" "+(i + 1) + ". " + options.get(i));
            }

            // Get and validate user's answer
            int userAnswer = getUserAnswer(options.size());

            // Check if the user's answer is correct
            if (userAnswer - 1 == question.getCorrectOptionIndex()) {
                System.out.println("------------------------------------------------------");
                System.out.println("                  |Correct Answer|                      ");
                System.out.println("------------------------------------------------------");
                countRight++;
            } else {
                System.out.println("------------------------------------------------------");
                System.out.println("                  |Wrong Answer|                      ");
                System.out.println("------------------------------------------------------");
                countWrong++;
            }
            countTotal++;
        }

        // Display the quiz result
        Result result = new Result(countTotal, countRight, countWrong);
        result.showResult();
    }

    // Private method to get and validate user's answer
    private int getUserAnswer(int optionsCount) {
        Scanner sc = new Scanner(System.in);
        int userAnswer = -1;
        do {
            System.out.print("Enter your answer (1-" + optionsCount + "): ");
            try {
                userAnswer = sc.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

        } while (userAnswer < 1 || userAnswer > optionsCount);
        return userAnswer;
    }
}

// Interface representing the result of the quiz
interface IResult {
    void showResult();

    double showPercentage(int correctAnswers, int totalQuestions);

    String showPerformance(double percentage);
}

// Represents the result of the quiz
class Result implements IResult {
    int totalQuestions;
    int correctAnswers;
    int wrongAnswers;

    // Constructor for creating a quiz result
    public Result(int totalQuestions, int correctAnswers, int wrongAnswers) {
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
    }

    // Display the quiz result
    public void showResult() {
        System.out.println("|-----------------------------|");
        System.out.println("|       Your results        |");
        System.out.println("|-----------------------------|");
        System.out.println("-> Total Questions : " + totalQuestions);
        System.out.println("-> Number of correct answers : " + correctAnswers);
        System.out.println("-> Number of wrong answers : " + wrongAnswers);
        System.out.println("-> Percentage : " + showPercentage(correctAnswers, totalQuestions));
        System.out.println("-> Your performance : " + showPerformance(showPercentage(correctAnswers, totalQuestions)));
    }

    // Calculate and return the percentage of correct answers
    public double showPercentage(int correctAnswers, int totalQuestions) {
        return (double) (correctAnswers / (double) totalQuestions) * 100;
    }

    // Determine and return the performance based on the percentage
    public String showPerformance(double percentage) {
        String performance = "";
        if (percentage > 80) {
            performance = "Good";
        } else if (percentage < 30) {
            performance = "Poor";
        } else
            performance = "Average";
        return performance;
    }
}

// The main class to run the quiz application
public class Main {
    public static void main(String[] args) {
        // Sample questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("1.What is a unit of data storage in a computer's memory?", List.of("Byte", "Bit", "Cell"), 0));
        questions.add(new Question("2.Which data structure follows the Last In, First Out (LIFO) principle?", List.of("Queue", "Stack", "List"), 1));
        questions.add(new Question("3.Which type of loop in Java does not have a predetermined end?", List.of("For", "While", "Do"), 1));
        questions.add(new Question("4.In object-oriented programming, what is the process of combining data and methods into a single unit known as?", List.of("Inherit", "Encapsulate", "Extend"), 1));
        questions.add(new Question("5.What is the term for a method that is automatically called when an object is created?", List.of("Init", "Start", "Constructor"), 2));
        questions.add(new Question("6.In computer science, what is the purpose of an algorithm?", List.of("Sort", "Solve", "Code"), 1));
        questions.add(new Question("7.What is the primary function of the break statement in Java?", List.of("Stop", "Skip", "Go"), 0));
        questions.add(new Question("8.Which sorting algorithm has an average time complexity of O(n log n)?", List.of("Bubble", "Merge", "Insert"), 1));
        questions.add(new Question("9.In Java, what keyword is used to declare a constant variable?", List.of("Final", "Const", "Static"), 0));
        questions.add(new Question("10.Which sorting algorithm has a quadratic time complexity of O(n^2) in the worst case?", List.of("Quick", "Bubble", "Merge"), 1));

        // Create a quiz
        Quiz quiz = new Quiz(questions);

        // Take the quiz
        quiz.takeQuiz();
    }
}
