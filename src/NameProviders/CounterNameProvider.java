package NameProviders;

import java.io.File;
import java.util.LinkedList;
import java.util.Stack;

public class CounterNameProvider implements NameProvider {
    long counter;
    Stack <Long> stack = new Stack<>();

    public CounterNameProvider() {counter = 0;}

    @Override
    public void resetState() { stack.push(counter); counter = 0; }

    @Override
    public void inferOldState() { counter = stack.pop(); }

    @Override
    public String getName(File file) { return Long.toString(counter++);}
}
