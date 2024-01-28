import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> instrList = new ArrayList<>();


    public TaskList(ArrayList<Task> arr) {
        this.instrList = arr; 
    }

    public enum TaskCommand {
        TODO, DEADLINE, EVENT
    }

    public void listOut() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.instrList.size() ; i++) {
            System.out.println(i + 1 + "."+ this.instrList.get(i).toString());
        }
    }

    public void addTask(TaskCommand cmd, String instr, Storage thiStorage) throws DukeException {
        switch (cmd) {
            case TODO: 
                try {
                    Todo taskTodo = new Todo(instr.split("todo ")[1]);
                    this.instrList.add(taskTodo); 
                    thiStorage.saveTaskList(this.instrList);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskTodo.toString());
                } catch (ArrayIndexOutOfBoundsException e ){
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty. \nTry again!"); 
                }
                break;
            case DEADLINE: 
                try {
                    String deadlineDescription = instr.substring(9);
                    String[] tskNames = deadlineDescription.split(" /by ");
                    Deadline taskDeadline = new Deadline(tskNames[0], tskNames[1]); 
                    this.instrList.add(taskDeadline); 
                    thiStorage.saveTaskList(this.instrList);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskDeadline.toString());
                } catch(StringIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! You cannot leave the description of a deadline to be empty. \nTry again!");
                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! You missed out the deadline time of this task. \nTry again!");
                }
                break;
            case EVENT: 
                try {
                    String eventDescription = instr.substring(6);
                    String[] instrsubString = eventDescription.split(" /from ");
                    String name = instrsubString[0]; 
                    String[] startAndEnd = instrsubString[1].split(" /to "); 
                    Events taskEvent = new Events(name, startAndEnd[0], startAndEnd[1]);
                    this.instrList.add(taskEvent); 
                    thiStorage.saveTaskList(this.instrList);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskEvent.toString()); 
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! You cannot leave the description of an event to be empty. \nTry again!");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! You missed out the date of this task. \nTry again!");
                }
                break;
            
            default:
            throw new DukeException("OOPS!!! What is that? I'm sorry, but I don't recognise this command :-( \nTry another command!");  
        }              
        System.out.println("Now you have " + this.instrList.size() + " tasks in the list."); 
    }

    public void mark(String instr, Storage thiStorage) throws DukeException {
        try {
            int instrNum = Integer.valueOf(instr.split(" ")[1]) - 1;
            String res = this.instrList.get(instrNum).markAsDone(); 
            thiStorage.saveTaskList(this.instrList);
            System.out.println("Nice! I've marked this task as done:" + "\n" + res); 
        } catch (NullPointerException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number. \nTry again with a different task number!"); 
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number."); 
        }
    }

    public void unmark(String instr, Storage thiStorage) throws DukeException {
        try {
            int instrNum = Integer.valueOf(instr.split(" ")[1]) - 1;
            String res = this.instrList.get(instrNum).markAsUndone(); 
            thiStorage.saveTaskList(this.instrList);
            System.out.println("OK, I've marked this task as not done yet:" + "\n" + res); 
        } catch (NullPointerException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number. \nTry again with a different task number!"); 
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number."); 
        }
    } 

    public void delete(String instr, Storage thiStorage) throws DukeException { 
        try {
            int ptr = Integer.valueOf(instr.split(" ")[1]) - 1; 
            Task str = this.instrList.get(ptr); 
            this.instrList.remove(ptr); 
            thiStorage.saveTaskList(this.instrList);
            System.out.println("Noted. I've removed this task: \n" + str.toString() + "\nNow you have " + this.instrList.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number."); 
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number. \nTry again with a different task number!"); 
        } 
    }

}