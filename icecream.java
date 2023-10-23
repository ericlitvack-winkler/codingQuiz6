import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import javafx.util.Pair;

class Result {
    static List<List<Integer>> solutions = new ArrayList<>(new ArrayList<>());

    /*
     * Complete the 'icecreamParlor' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER m
     *  2. INTEGER_ARRAY arr
     */

    public static int sumArray(List<Integer> arr, List<Integer> indices, int i, int sum) {
        if (i == 0) {
            return sum;
        } else {
            return sumArray(arr, indices, i - 1, sum + arr.get(indices.get(i)));
        }
    }

    public static Pair<Boolean, List<Integer>> solveIceCream(List<Integer> arr, int i, int m, int numIceCream, List<Integer> indices) {
        //Pair<List<Integer>, Integer> sub = new pair <> (indices, minTotal)
        Pair<Boolean, List<Integer>> correct = new Pair<>(true, indices);
        Pair<Boolean, List<Integer>> none = new Pair<>(false, indices);
        if (m >= 0 && numIceCream == 3) {
            solutions.add(indices);
            return correct;

        }
        if (m < 0 || i == -1) {
            return none;
        }


        Pair<Boolean, List<Integer>> pathway;
        pathway = solveIceCream(arr, i - 1, m, numIceCream, indices);
        if (pathway.getKey()) {
            return pathway;
        }
        pathway = solveIceCream(arr, i - 1, m - arr.get(i), numIceCream + 1, indices);
        if (pathway.getKey()) {
            indices.add(i + 1);
            return pathway;
        }
        return none;

    }

    public static List<Integer> maxSum(List<List<Integer>> solutions, int i, int maxSum, int maxArray, int currSum, List<Integer> arr) {
        if (i == 0) {
            return maxArray;
        } else {
            currArray = solutions.get(i);
            currSum = sumArray(arr, currArray, currArray.size() - 1, 0);
            if (currSum > maxSum) {
                maxSum = currSum;
                maxArray = i;
            }
        }
    }

    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
        // Write your code here
        System.out.println(solutions);
        List<Integer> indices = new ArrayList<>();
        Pair<Boolean, List<Integer>> result = solveIceCream(arr, arr.size() - 1, m, 0, indices);
        //List<Integer> empty =
        int max = maxSum(solutions, solutions.size() - 1, 0, 1, arr);
        return solutions.get(max);

        //return result.getValue();


    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int m = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<Integer> result = Result.icecreamParlor(m, arr);

                bufferedWriter.write(
                        result.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                                + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
