package fr.anthonyquere.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    public static void main(String[] args) {
        startFizzBuzz(2);
    }

    public static List<String> startFizzBuzz(int count) {
        List<String> FizzBuzzList = new ArrayList<>();
        for(int i = 1 ; i <= count ; i ++ ){
            if(i % 3 == 0 && i % 5 != 0){
                FizzBuzzList.add("Fizz");
            }
            else if(i % 5 == 0 && i % 3 != 0){
                FizzBuzzList.add("Buzz");
            }
            else if(i % 5 != 0 && i % 3 != 0 ){
                FizzBuzzList.add(String.valueOf(i));
            }
            else if(i % 3 == 0 && i % 5 == 0){
                FizzBuzzList.add("FizzBuzz");
            }

        }



        return FizzBuzzList;

    }
}
