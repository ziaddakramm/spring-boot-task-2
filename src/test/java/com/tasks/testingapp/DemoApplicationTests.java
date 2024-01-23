package com.tasks.testingapp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class DemoApplicationTests {

    Calculator underTest=new Calculator();

    @Test
    void itShouldAddNumbers(){
        // given
        int n1=20;
        int n2=30;

        //when
        int expected=50;
        int result=underTest.add(n1,n2);

        //then
        assertThat(result).isEqualTo(expected);
    }
    class Calculator{
        int add(int a,int b){
            return a+b;
        }
    }
}
