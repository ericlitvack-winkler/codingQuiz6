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

class Result {

    /*
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void plusMinus(List<Integer> arr) {
        // Write your code here
        double n = arr.size();
        double pos = 0;
        double neg = 0;
        double zero = 0;

        for (int i : arr) {
            if (i > 0) {
                pos += 1;
            } else if (i < 0) {
                neg += 1;
            } else {
                zero += 1;
            }
        }
        pos = pos / n;
        neg = neg / n;
        zero = zero / n;

        DecimalFormat df = new DecimalFormat("###.######");

        System.out.println(df.format(pos));
        System.out.println(df.format(neg));
        System.out.println(df.format(zero));
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        Result.plusMinus(arr);

        bufferedReader.close();
    }
}
