package com.gmm.utils;

interface Fruit { 
    public void eat() ; 
} 
class Apple implements Fruit { 
    public void eat() { 
        System.out.println("Eat apple.") ; 
    } 
} 
class Orange implements Fruit { 
    public void eat() { 
        System.out.println("Eat orange.") ; 
    } 
} 
class Factory { 
    public static Fruit getInstance(String className) { 
        if ("apple".equals(className)) { 
            return new Apple() ; 
        } 
        if ("orange".equals(className)) { 
            return new Orange () ; 
        } 
        return null ; 
    } 
} 
public class FactoryDemo { 
    public static void main(String args[]) { 
        Fruit f = Factory.getInstance("orange") ; 
        f.eat() ; 
    } 
}
