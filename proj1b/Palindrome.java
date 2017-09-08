public class Palindrome {
    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> deq = new ArrayDequeSolution<>();
        for (int i = 0; i < word.length(); i += 1) {
            char chr = word.charAt(i);
            deq.addLast(chr);
        }
        return deq;
    }

    public static boolean isPalindrome(String word) {
        Deque<Character> deq = wordToDeque(word);
        if (deq.size() == 0 || deq.size() == 1) {
            return true;
        } else if (deq.get(0) == deq.get(deq.size() - 1 )) {
            return isPalindrome(word.substring(1, word.length() - 1));
        } else {
            return false;
        }

        /* Alternative solution without using Deque.
        int lastIndex = word.length() - 1;
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else if (word.charAt(0) == word.charAt(lastIndex)) {
            return isPalindrome(word.substring(1, lastIndex));
        } else {
            return false;
        }
        */
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deq = wordToDeque(word);
        if (deq.size() == 0 || deq.size() == 1) {
            return true;
        } else if (cc.equalChars(deq.get(0), deq.get(deq.size() - 1 ))) {
            return isPalindrome(word.substring(1, word.length() - 1), cc);
        } else {
            return false;
        }

        /* Alternative solution without using Deque.
        int lastIndex = word.length() - 1;
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else if (cc.equalChars(word.charAt(0), word.charAt(lastIndex))) {
            return isPalindrome(word.substring(1, lastIndex), cc);
        } else {
            return false;
        }
        */
    }
}
