package com.spare.house.dao;

import org.junit.Test;

/**
 * Created by dada on 2017/8/12.
 */
public class UtilTest {

    @Test
    public void testReplace() {
        String year = "\n" +
                "                                        \t\n" +
                "                                        \t\t2001~2005年\n" +
                "                                        \t\n" +
                "                                        \t\n" +
                "                                        ";
        year = year.replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "");
        System.out.println(year);
    }

}
