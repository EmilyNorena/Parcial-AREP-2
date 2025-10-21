package edu.eci.arep.mathServices;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;

@RestController
public class mathController {

    @GetMapping("/linearSearch")
    public int linearSearch(@RequestParam int[] list, int value){
        for (int i = 0; i < list.length; i++){
            if (list[i] == value){
                return i;
            }
        }
        return -1;
    }

    @GetMapping("/binarySearch") //Ordenado
    public int binarySearch(@RequestParam int[] list, int value){
        return binarySearchImpl(list, value);
    }

    private int binarySearchImpl(int [] list, int value){
        if (list.length > 0){
            int midIndex = list.length / 2;
            if (value == list[midIndex]){
                return midIndex;
            }
            else if (value > list[midIndex]){

                binarySearchImpl(subList(list, midIndex + 1, list.length - 1), value);
            } else{
                binarySearchImpl(subList(list, 0, midIndex - 1), value);
            }
            return -1;
        }
        return -1;
    }

    private int[] subList(int[] list, int start, int end){
        int[] newList = null;
        for (int i = 0; i <= end; i++){
            newList[i] = list[i];
        }
        return newList;
    }
}
