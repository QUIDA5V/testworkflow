package com.vip.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class S3FolderTest {

    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(String.valueOf(5), calc.add(2, 3), 5);
    }
}
