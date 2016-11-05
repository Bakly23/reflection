package ru.sberbank.bit.kolpakov.java.reflection;

import org.junit.Test;
import ru.sberbank.bit.kolpakov.java.refleciton.BeanUtils;
import ru.sberbank.bit.kolpakov.java.refleciton.Person;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Georgii Kolpakov on 05.11.16.
 */
public class BeanUtilsTest {
    @Test
    public void test() {
        Person from = new Person("Gosha", "Kolpakov", 21, 35, 42, 620135);
        Person to = new Person();
        BeanUtils.assign(to, from);
        assertEquals(to.getFirstName(), "Gosha");
        assertEquals(to.getSecondName(), "Kolpakov");
        assertNull(to.getInn());
        assertEquals(to.getLock(), "35");
        assertEquals(to.getPostCode(), new Integer(620135));
    }
}
