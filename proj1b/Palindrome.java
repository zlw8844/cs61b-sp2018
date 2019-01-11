public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        /*ArrayDeque<Character> res = new ArrayDeque<Character>();*/
        LinkedListDeque<Character> res = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i += 1){
            res.addLast(word.charAt(i));
        }
        return res;
    }

    private boolean isPalindromeHelper(Deque<Character> w){
        if (w.size() <= 1){
            return true;
        } else {
            Character f = w.removeFirst();
            Character e = w.removeLast();
            if (f.equals(e)) {
                return isPalindromeHelper(w);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word){
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);
    }

    private boolean isPalindromeHelper(Deque<Character> w, CharacterComparator cc){
        if (w.size() <= 1){
            return true;
        } else {
            Character f = w.removeFirst();
            Character e = w.removeLast();
            if (cc.equalChars(f, e)) {
                return isPalindromeHelper(w, cc);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque, cc);

    }

}
