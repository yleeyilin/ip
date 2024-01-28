package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest(){
        Deadline tsk = new Deadline("project meeting", "Sunday");
        assertEquals("[D][ ] project meeting (by: Sunday)", tsk.toString());
    }

    @Test
    public void toSaveTest(){
        Deadline tsk = new Deadline("project meeting", "Sunday");
        assertEquals("D|0|project meeting|Sunday", tsk.toSave());
    }

    @Test
    public void dateTimeTest(){
        Deadline tsk = new Deadline("return book", "2/12/2019 1800");
        assertEquals("D|0|return book|Dec 2 2019 6:00 PM", tsk.toSave());
    }

    @Test
    public void dateTest(){
        Deadline tsk = new Deadline("return book", "2/12/2019");
        assertEquals("D|0|return book|Dec 2 2019", tsk.toSave());
    }
}