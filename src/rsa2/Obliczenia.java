/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa2;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author Wojciech
 */
public class Obliczenia {
    private BigInteger n;//n - modul
    private BigInteger d;//d - wykladnik prywatny
    private BigInteger e;//e - wykladnik klucza publiczngo
    private int bitlen = 256;//dlugosc klucza

    //kostruktor ktory moze szyfrowac uzywajac cudzego klucza publicznego
    public Obliczenia(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
    }
    
    //kostruktor ktory moze odszyfrowac uzywajac zewnetrznego klucza prywatnego
    public Obliczenia(BigInteger n, BigInteger d, int i) {
        this.n = n;
        this.d = d;
    }

    //szyfrowanie i deszyfrowanie
    public Obliczenia(int bits) {
        bitlen = bits;
        //klasa SecureRandom zapewnia "cryptographically strong random number generator (RNG)."
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);//liczby pierwsze
        BigInteger q = new BigInteger(bitlen / 2, 100, r);//liczby pierwsze
        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q
                .subtract(BigInteger.ONE));
        
        //szukam liczby e
        e = new BigInteger("3");
        while (phi.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        //d - liczba odwrotna modulo do liczby e
        d = e.modInverse(phi);
    }

    //szyfrowanie danej wiadomosci
    public synchronized String szyfruj(String message) {
        return (new BigInteger(message.getBytes())).modPow(e, n).toString();
    }

    //szyfrowanie danej wiadomosci
    public synchronized BigInteger szyfruj(BigInteger message) {
        return message.modPow(e, n);
    }

    //odszyfruj dany szyfrogram
    public synchronized String deszyfruj(String message) {
        return new String((new BigInteger(message)).modPow(d, n).toByteArray());
    }

    //odszyfruj dany szyfrogram
    public synchronized BigInteger deszyfruj(BigInteger message) {
        return message.modPow(d, n);
    }

    //generuje klucz publiczny i prywatny
    public void generujKlucze() {
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q
                .subtract(BigInteger.ONE));
        //szukam liczby e
        e = new BigInteger("3");
        while (phi.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        d = e.modInverse(phi);
    }

    public void pokazKlucze()
    {
        System.out.println("KLUCZ PUBLICZNY\n" +
          "wykladnik e = " + e
                + "\n    modul n = " + n
                + "\n\nKLUCZ PRYWATNY\n" +
          "wykladnik d = " + d + "\n" + "Publiczny to (e,n) a Prywatny to (d,n)");
    }
    
    
    //modul
    public BigInteger getN() {
        return n;
    }

    //wykladnik publiczny
    public BigInteger getE() {
        return e;
    }
}
