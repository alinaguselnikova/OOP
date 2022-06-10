package nsu.computerScience.stackImplementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertThrows;


class StackTest {

   /* public static void main(String[] args) {
        Stack<Integer> exampleSt = new Stack<>();
        exampleSt.push(2);
        exampleSt.push(7);
        Stack<Integer> anotherSt = new Stack<>();
        anotherSt.push(4);
        anotherSt.push(8);
        exampleSt.pushStack(anotherSt);
        exampleSt.pop();
        exampleSt.popStack(2);

    }
    */

    @Test
    public void testExample() {
        Stack<Integer> exampleStack = new Stack<>();
        exampleStack.push(2);
        exampleStack.push(7);
        Stack<Integer> anotherStack = new Stack<>();
        anotherStack.push(4);
        anotherStack.push(8);
        exampleStack.pushStack(anotherStack);
        exampleStack.pop();
        exampleStack.popStack(2);
        Assertions.assertEquals(2, exampleStack.pop());
        assertThrows(IndexOutOfBoundsException.class, exampleStack::pop);
    }

    Stack<Integer> tStack = new Stack<>();
    ArrayList<Integer> arr;

    {
        arr = new ArrayList<>();
    }


    @Test
    public void testPopPush() {
        tStack.push(5);
        tStack.push(4);
        tStack.push(3);
        Assertions.assertEquals(3, tStack.pop());
        Assertions.assertEquals(4, tStack.pop());
        Assertions.assertEquals(5, tStack.pop());
    }

    @Test
    public void testPushStack() {
        Stack<Integer> exStack = new Stack<>();

        exStack.push(5);
        exStack.push(4);
        //exStack.stack = null;
        exStack.push(3);
        tStack.pushStack(exStack);
        Assertions.assertEquals(3, tStack.pop());
        Assertions.assertEquals(4, tStack.pop());
        Assertions.assertEquals(5, tStack.pop());
    }

    @Test
    public void testPopStack() {
        tStack.push(5);
        tStack.push(4);
        tStack.push(3);
        Stack<Integer> PopStack = tStack.popStack(2);
        Assertions.assertEquals(3, PopStack.pop());
        Assertions.assertEquals(4, PopStack.pop());
        assertThrows(IndexOutOfBoundsException.class, PopStack::pop);
    }

    @Test
    public void testIterator() {
        ArrayList <Integer> arr = new ArrayList<>();
        for (var i = 0; i < 10; i++) {
            tStack.push(i);
            arr.add(i);
        }
        Iterator<Integer> iter = tStack.iterator();
        Iterator<Integer> iterArr = arr.iterator();
        for (var elem : tStack) {
        Assertions.assertEquals(iter.next(), iterArr.next());
        }
    }
    @Test
    public void TestIterator20(){
        ArrayList <Integer> arr = new ArrayList <> ();
         int size = 20;
    }

}