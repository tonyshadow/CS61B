/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        CharacterComparator offByOne = new OffByOne();
        CharacterComparator offByThree = new OffByN(3);
        In in = new In("words.txt");

        System.out.println("Results with normal definition of palindrome:");
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && Palindrome.isPalindrome(word)) {
                System.out.println(word);
            }
        }

        System.out.println("\nResults with OffByOne definition of palindrome:");
        in = new In("words.txt");
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && Palindrome.isPalindrome(word, offByOne)) {
                System.out.println(word);
            }
        }

        System.out.println("\nResults with OffByThree normal definition of palindrome:");
        in = new In("words.txt");
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && Palindrome.isPalindrome(word, offByThree)) {
                System.out.println(word);
            }
        }
    }
} 
