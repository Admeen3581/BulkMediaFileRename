package NameProviders;

import java.io.File;
import java.util.LinkedList;
import java.util.Stack;

public class CounterNameProvider implements NameProvider {
    long counter;
    long initialValue;

    Stack <Long> stack = new Stack<>();

    public CounterNameProvider() {initialValue = 0; counter = initialValue;}

    public CounterNameProvider(long initialValue) {this.initialValue = initialValue; counter = initialValue;}

    @Override
    public void resetState() { stack.push(counter); counter = initialValue; }

    @Override
    public void inferOldState() { counter = stack.pop(); }

    @Override
    public String getName(File file) { return Long.toString(counter++);}
}
