package com.xiaowei.xiaobai;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    
    @Test
    public void testByteScope() {
        /*********byte:占用空间比较少，只占用一个字节，一个字节占用了8个比特位
         * 8个比特位所能够表示的所有可能的值的数量是256个（16 * 16），在java语
         * 言中，8个比特位当中，最高位是用来表示正负的，还剩下7位一共是128个。
         * 在byte类型当中，它能够表示的值是-128 -  -1(128个), 0 -127 （128个)
         * 对于这种小字节的使用，它仅仅适合于计数不太大的环境下被采用。如一个班级的学生总数。***/
        /*********java中byte127 +1 = ? ***/
        byte a = 127; //  127二进制:01111111
        //  解析:01111111 + 1 = 10000000， 10000000的十进制是128，然后取反，得出来是01111111
        System.out.println(++a); // -128

        /******a.getClass.toString() 得出数据类型**************/
    }

    @Test
    public void testShortScope() {
        /**
         * short类型是两个字节的长度。
         * byte取值范围是 -128 --- 127。
         * 0000 0000  0000 0000
         * 那么short能够表示的最大值和最小值的有效范围分别是多少呢？ 256 * 128 = 32768
         *  -32768   ----   32767
         */
    }

    @Test
    public void testLongScope() {
        /**
         * java中的long类型它的长度是8个字节，而int类型是4个字节。
         * long类型是64位。
         */
        // int类型不能够装下。  long类型需要在后面加l/L.
//        long l1 = 11999999999999;
        long l1 = 11999999999999l;
    }

    @Test
    public void testIntScope() {
        /**
         * 在java中int类型的长度是4个字节，32位。
         * 0000 0000 0000 0000 0000 0000 0000 0000
         *   128            256      256       256
         */
        int a = 12;
        /****** 最大值: 2147483647最小值: -2147483648 ***/
        System.out.println("最大值: " + Integer.MAX_VALUE +  "最小值: " +Integer.MIN_VALUE);
        /**
         * 采用了封装类来获取不同类型的最大值和最小值。
         * Integer:是int类型的封装类.
         * Short:是short类型的封装类。
         * Byte:是byte类型的封装类。
         * 在java中，默认的数值类型其实就是int类型。通常我们看到一个整型数据类型的时候其实就是int类型。
         */
        byte b1 = 102;
        byte b2 = 120;
        // byte b3 = b1 + 12;  12是四个字节的数据类型int。  做运算编译器会提示。
    }

    @Test
    public void testCharScope() {
        /**
         * 必须使用单引号引起来，通常表示单个字符。
         * java语言使用16位的unicode字符集作为编码方式。
         */
        char c = 'w';
        char c1 = '1';
        System.out.println("c: " + c + "  c1: " + c1);
        char c2 = '我';
        System.out.println("c2:" + c2);
    }
}