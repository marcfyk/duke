package duke.task;

import duke.storage.Storage;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /** ArrayList of tasks including ToDos, Deadlines, Events. */
    private ArrayList<Task> list;

    /**
     * Creates an instance of an empty TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Creates an instance of a TaskList.
     * Loads tasks stored on data file.
     *
     * @param storage Storage object to access data file.
     */
    public TaskList(Storage storage) {
        this();
        load(storage);
    }

    /**
     * Loads the tasks from storage file into TaskList object.
     *
     * @param storage Storage instance to load files.
     */
    public void load(Storage storage) {
        ArrayList<Task> tasks = storage.parseFile();
        list.clear();
        for (Task task : tasks) {
            list.add(task);
            Task.addToTotalTasks();
        }
    }

    /**
     * Gets the total number of tasks in this list.
     *
     * @return Total number of tasks in the list.
     */
    public int getTotalTasks() {
        return list.size();
    }

    /**
     * Adds Task to list.
     *
     * @param task Task to be added.
     * @return Task that was added.
     */
    public Task addTask(Task task) {
        list.add(task);
        return task;
    }

    /**
     * Does Task at the given 1-based index.
     *
     * @param index Index of task to check (1-based).
     * @return Task that was done.
     */
    public Task doTask(int index) {
        Task task = getTask(index);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes a task at given 1-based index to delete.
     *
     * @param index Index of the task to delete (1-based).
     * @return Task that was deleted. Null if index is out of range.
     */
    public Task deleteTask(int index) {
        if (index > list.size()) {
            return null;
        }
        Task task = getTask(index);
        list.remove(index - 1);
        return task;
    }

    /**
     * Gets a Task from the list.
     *
     * @param index index of task to get (1-based).
     * @return Task object.
     */
    public Task getTask(int index) {
        return list.get(index - 1);
    }

    /**
     * Gets an ArrayList of all tasks in the task list.
     *
     * @return ArrayList of all tasks in the task list.
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(list);

    }

    /**
     * Filters out all tasks, whose description contains the substring,
     * and returns a new task list with those tasks.
     *
     * @param substring Substring that is to be tested against all tasks' description.
     * @return Returns a new TaskList with tasks' descriptions contains the substring.
     */
    public TaskList filterByString(String substring) {
        TaskList tasklist = new TaskList();
        for (Task task : list) {
            if (task.getDescription().contains(substring)) {
                tasklist.addTask(task);
            }
        }
        return tasklist;
    }

    /**
     * Returns String representation of the task list.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String indent = String.format("%5s", "");
        StringBuilder strb = new StringBuilder(indent + "Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); ++i) {
            strb.append(indent).append(i + 1).append(".").append(list.get(i));
            if (i < list.size() - 1) {
                strb.append("\n");
            }
        }
        return strb.toString();
    }
}
