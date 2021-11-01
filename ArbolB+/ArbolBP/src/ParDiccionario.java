public class ParDiccionario {
    int key;
    double value;

    public ParDiccionario(int key, double value) {
        this.key = key;
        this.value = value;
    }

    public int compareTo(ParDiccionario o){
        if (key == o.key) {
            return 0;
        } else if (key > o.key) {
            return 1;
        } else {
            return -1;
        }
    }
}
