package edu.eci.arep.mathServices;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;

@RestController
@CrossOrigin(origins = "*")
public class mathController {

    @GetMapping("/linearSearch")
    public String linearSearch(@RequestParam int[] list, int value){
        int index = -1;
        String strList = listToString(list);
        for (int i = 0; i < list.length; i++){
            if (list[i] == value){
                index = i;
            }
        }
        return String.format(
                "{ \"operation\": \"%s\", \"inputList\": \"%s\", \"value\": \"%s\", \"output\": \"%s\" }",
                "linearSearch", strList, value, index
        );

    }

    @GetMapping("/binarySearch") //Ordenado
    public String binarySearch(@RequestParam int[] list, int value){
        int index = binarySearchImpl(list, value);
        String strList = listToString(list);
        return String.format(
                "{ \"operation\": \"%s\", \"inputList\": \"%s\", \"value\": \"%s\", \"output\": \"%s\" }",
                "binarySearch", strList, value, index
        );
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
        }
        return -1;
    }

    private int[] subList(int[] list, int start, int end) {
        int newSize = end - start + 1;
        int[] newList = new int[newSize];
        for (int i = start; i <= end; i++) {
            newList[i - start] = list[i];
        }
        return newList;
    }

    private String listToString(int [] list){
        String strList = "";
        for (int i = 0; i < list.length; i++){
            strList = strList + list[i] + ",";
        }
        return strList;
    }
}
