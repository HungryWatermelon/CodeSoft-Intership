import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText = "";

        System.out.println("Welcome to the Word Counter!");

        // Prompt the user to enter text or provide a file name
        System.out.print("Enter text or provide a file name: ");
        String userInput = scanner.nextLine();

        // Read the input text or file
        try {
            BufferedReader reader;
            if (isFile(userInput)) {
                reader = new BufferedReader(new FileReader(userInput));
                String line;
                while ((line = reader.readLine()) != null) {
                    inputText += line + " ";
                }
                reader.close();
            } else {
                inputText = userInput;
            }

            // Split the text into words using space or punctuation as delimiters
            String[] words = inputText.split("[\\s\\p{Punct}]+");

            // Initialize a counter variable for word count
            int wordCount = words.length;

            // Display the total count of words
            System.out.println("Total word count: " + wordCount);

            // Enhancements (additional features):
            // 7. Ignoring common words or stop words.
            // 8. Providing statistics like the number of unique words and word frequency.
            Map<String, Integer> wordFrequency = new HashMap<>();
            for (String word : words) {
                // Convert words to lowercase to make the count case-insensitive
                word = word.toLowerCase();

                // Ignore common words (you can extend this list)
                String[] commonWords = {"the", "and", "in", "to", "of", "a", "is", "it"};
                boolean isCommonWord = false;
                for (String common : commonWords) {
                    if (word.equals(common)) {
                        isCommonWord = true;
                        break;
                    }
                }

                if (!isCommonWord) {
                    // Count word frequency
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }

            // Display statistics
            System.out.println("Unique word count: " + wordFrequency.size());
            System.out.println("Word frequency:");
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    // Check if the input is a file (based on whether it contains a file extension)
    private static boolean isFile(String input) {
        return Pattern.matches(".*\\.[a-zA-Z0-9]+", input);
    }
}


