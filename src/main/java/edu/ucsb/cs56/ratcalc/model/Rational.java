package edu.ucsb.cs56.ratcalc.model;
/**
 * A class to represent a rational number with a numerator and denominator
 * 
 * @author P. Conrad for CS56 F16
 * 
 */

public class Rational {

    private int num;
    private int denom;

    /**
     * greatest common divisor of a and b
     * 
     * @param a first number
     * @param b second number
     * @return gcd of a and b
     */
    public static int gcd(int a, int b) {
        if (a == 0)
            return b;
        else if (b == 0)
            return a;
        else
            return gcd(b % a, a);
    }

    public Rational() {
        this.num = 1;
        this.denom = 1;
    }

    public Rational(int num, int denom) {
        if (denom == 0) {
            throw new IllegalArgumentException("denominator may not be zero");
        }
        this.num = num;
        this.denom = denom;
        if (num != 0) {
            int gcd = Rational.gcd(num, denom);
            this.num /= gcd;
            this.denom /= gcd;
            if(this.denom < 0) {
                this.num *= -1;
                this.denom *= -1;
            }
        }
    }

    public String toString() {
        if (denom == 1 || num == 0)
            return "" + num;
        return num + "/" + denom;
    }

    public int getNumerator() {
        return this.num;
    }

    public int getDenominator() {
        return this.denom;
    }

    public Rational times(Rational r) {
        return new Rational(this.num * r.num, this.denom * r.denom);
    }

    public static Rational product(Rational a, Rational b) {
        return new Rational(a.num * b.num, a.denom * b.denom);
    }

    /**
     * least common multiple of a and b
     * 
     * @param a first number
     * @param b second number
     * @return lcm of a and b
     */
    public static int lcm(int a, int b) {
        return Math.abs((a*b)/gcd(a,b));
    }

    /**
     * @param r other rational number
     * @return this number plus r
     */
    public Rational plus(Rational r) {
        return new Rational(this.num * r.getDenominator() + this.denom * r.getNumerator(), this.denom * r.getDenominator());
    }

    /**
     * @param a first rational number
     * @param b second rational number
     * @return a+b
     */
    public static Rational sum(Rational a, Rational b) {
        return a.plus(b);
    }

    /**
     * @param r other rational number
     * @return this number minus r
     */
    public Rational minus(Rational r) {
        Rational r1 = new Rational(-1 * r.getNumerator(), r.getDenominator()); 
        return this.plus(r1);
        
    }

    /**
     * @param a first rational number
     * @param b second rational number
     * @return a-b
     */
    public static Rational difference(Rational a, Rational b) {
        return a.minus(b);
    }

    /**
     * swaps numerator and denominator
     * 
     * @return reciprocal If numerator if zero, throws an instance of
     *         java.lang.ArithmeticException.
     */
    public Rational reciprocalOf() {
        if (this.num == 0) { throw new ArithmeticException("numerator may not be zero"); } 
        return new Rational(this.denom, this.num);
    }

    /**
     * @param r other rational number
     * @return this number divided by r
     */
    public Rational dividedBy(Rational r) {
        return this.times(r.reciprocalOf());
    }

    /**
     * @param a first rational number
     * @param b second rational number
     * @return a divided by b
     */
    public static Rational quotient(Rational a, Rational b) {
        return a.dividedBy(b);
    }

    /**
     * For testing getters.
     * 
     * @param args unused
     */

    public static void main(String[] args) {
        Rational r = new Rational(5, 7);
        System.out.println("r.getNumerator()=" + r.getNumerator());
        System.out.println("r.getDenominator()=" + r.getDenominator());
    }

}
