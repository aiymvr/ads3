public class MyTestingClass {
    private int feature1;
    private int feature2;

    // Constructor
    public MyTestingClass(int feature1, int feature2) {
        this.feature1 = feature1;
        this.feature2 = feature2;
    }

    // Override equals to compare both features
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MyTestingClass)) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return this.feature1 == other.feature1 && this.feature2 == other.feature2;
    }

    // Custom hashCode to ensure uniform distribution
    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + feature1;
        hash = 31 * hash + feature2;
        return hash;
    }
}
