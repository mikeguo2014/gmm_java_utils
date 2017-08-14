package com.gmm.utils;

interface Subject { // 操作主题
    public void get() ; // 要银子
} 
class RealSubject implements Subject {  // 真正的要银子
    public void get() { 
        System.out.println("真实业务主题") ; 
    } 
} 
class ProxySubject implements Subject { 
    private Subject sub = null ; 
    public ProxySubject(Subject sub) { 
        this.sub = sub ; 
    } 
    public void prepare() { 
        System.out.println("准备操作。") ; 
    } 
    public void destroy() { 
        System.out.println("收尾操作。") ; 
    } 
    public void get() { 
        this.prepare() ; 
        this.sub.get() ; 
        this.destroy() ; 
    } 
} 
public class ProxyDemo { 
    public static void main(String args[]) { 
        Subject sub = new ProxySubject(new RealSubject()) ; 
        sub.get() ; 
    } 
} 
