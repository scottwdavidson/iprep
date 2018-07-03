package com.swd.j8;

public interface MultiplicationExtensionMethod {

    int multiply(int a, int b);

    default int square(int a){ return multiply(a, a); }
}
