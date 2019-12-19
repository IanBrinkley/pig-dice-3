public class Die {
    private int face;
    public Die() {
        roll();
    }
    public void roll() {
        face = (int) (Math.random()*6+1);
    }
    public int getFace() {
        return face;
    }
}