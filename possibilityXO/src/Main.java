import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
This program takes the input as a string. If the input includes '$', then it counts all the possibilities when
all the '$' can be replaced with 'X' and 'O'.
Then with the help of 2 nested for-loops, it replaces them and prints them as well.
With the help of try-catch, it also writes the input and all the possibilities in a .txt file.

Furthermore, it can count the time taken for the whole program to finish.

The main objective was to find out time taken by the array-list.
 */

public class Main {
    public static void main(String[] args) {
        PrintWriter output;
        // Using try-catch bcoz we are outputting to a .txt file.
        try{
            long start_time = System.nanoTime(), end_time;

            output = new PrintWriter(new FileOutputStream("output.txt", true));

            Scanner myObj = new Scanner(System.in);
            // Here lies the input.
            System.out.println("User, please     enter a good string, and do not be dumb about it!");
            System.out.println("Enter something like XOXO$$$ or something with $:");
            String input = myObj.nextLine();

            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Given String: " + input);
            output.write("\n\n+++++++++++++++++++++++++++++++++++++++++++++");
            output.write("\n\nGiven String: " + input + "\n");

            int count = 0;
            // Counting all the '$' in the string.
            for (int i = 0; i < input.length(); i++){
                if (input.charAt(i) == '$')
                    count++;
            }

            // Calculating all the possible scenarios with 'X' and 'O', that are replaced from '$'.
            int max_possibilites = (int)Math.pow(2,count);
            System.out.println("The number of possibilities are: " + max_possibilites);
            output.write("The number of possibilities are: " + max_possibilites + "\n");

            String poss_as_binary = "";
            int num_changeable = count;
            char[] input_string = input.toCharArray(); // Convert input into array

            // Outside for-loop goes upto all the possibilities there can be for replacing all '$'.
            for (int i = 0; i < max_possibilites; i++) {
                poss_as_binary = Integer.toBinaryString(i);
                /*
                ^ As we need to replace '$' with 2 chars (X and O) only, that is why it is easier to convert it
                into binary and then use it.
                */
                String temp = "";
                for (int j = 0; j < num_changeable-poss_as_binary.length(); j++) {
                    temp = temp + "0";
                } // This for loop converts all the single '0' in poss_as_binary to '00' (if there are 2 $).
                // If there are more '$' then it adds them accordingly.
                poss_as_binary = temp + poss_as_binary;
                int start_index = 0;
                input_string = input.toCharArray(); // Converting input into array again, so it won't
                // be affected by the loop.

                // This loop checks with poss_as_binary and converts them into X or O.
                for (int j = 0; j < input_string.length; j++) {
                    if (input_string[j] == '$') {
                        if (poss_as_binary.charAt(start_index) == '0') {
                            input_string[j] = 'O';
                        } else if (poss_as_binary.charAt(start_index) == '1') {
                            input_string[j] = 'X';
                        }
                        start_index++;
                    }
                }
                // Outputting all possibilities.
                System.out.println(input_string);
                output.write(new String(input_string) + "\n");
            }

            // Calculating time complexity.
            end_time = System.nanoTime();
            int total_time = (int) TimeUnit.MILLISECONDS.convert((end_time-start_time), TimeUnit.NANOSECONDS);

            // Printing time.
            System.out.println("\nProgram time taken: " + total_time + " Milli-Seconds\n");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
            output.write("\nProgram time taken: " + total_time + " Milli-Seconds\n");
            output.write("+++++++++++++++++++++++++++++++++++++++++++++");

            output.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such file exists!");
        }
    }
}