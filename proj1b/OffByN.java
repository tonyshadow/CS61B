public class OffByN implements CharacterComparator {
    private int d;

    public OffByN(int N) {
        d = N;
    }

    @Override
    public boolean equalChars(char a, char b) {
        int diff = a - b;
        return diff == d || diff == -d;
    }
}
