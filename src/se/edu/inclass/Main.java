package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlinesWithStreams(tasksData);

        System.out.println("Total number of deadlines (using streams): " +
                countDeadlinesWithStreams(tasksData));

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesWithStreams(ArrayList<Task> tasksData) {
        int count = (int) tasksData.stream()
                .filter(task -> task instanceof Deadline)
                .count();
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataWithStreams(ArrayList<Task> tasksData) {
        /*
         * Pass the function signature of the println method in System.out
         * Not System.out.println() which is a function call!
         */
        tasksData.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesWithStreams(ArrayList<Task> tasksData) {
        /*
         * Java will inter the data types of the parameters for a lambda function
         */
        tasksData.stream()
                .filter((task) -> task instanceof Deadline)
                .sorted((t1, t2) -> t1.getDescription().toLowerCase().compareTo(t2.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }

    public static void filterTasksByString(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filteredTasks = (ArrayList<Task>) tasksData.stream()
                .filter((task) -> task.getDescription().contains(filterString))
                .collect(toList());
    }
}
