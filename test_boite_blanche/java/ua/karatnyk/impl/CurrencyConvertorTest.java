package ua.karatnyk.impl;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class CurrencyConvertorTest extends TestCase {

    private CurrencyConversion conversion;

    @Before
    protected void setUp() throws ParseException {
        System.out.println("\nSetting up tests... \n");

        this.conversion  = new OfflineJsonWorker().parser();

    }

    @After
    protected void tearDown() {
        System.out.println("\nSuccess !");
    }

    public void testValidCurrency() throws ParseException {
        // Jeu de test pour plusieurs monnaies :
        ArrayList<String> currencys = new ArrayList(Arrays.asList("USD", "CHF", "INR", "AUD","MAD", "CUP", "JPY"));

        ArrayList<String> validCurrencys = new ArrayList(Arrays.asList("USD", "CAD", "GBP", "EUR", "CHF", "INR", "AUD"));

        IntStream.range(0, 9).forEachOrdered(n -> {
            Random rand = new Random();
            String e1 = currencys.get(rand.nextInt(currencys.size()));
            String e2 = currencys.get(rand.nextInt(currencys.size()));

            try {
                assert validCurrencys.contains(e1): "Uniquement entre les devises suivantes : USD, CAD, GBP, " +
                        "EUR, CHF, INR, AUD";

                assert validCurrencys.contains(e2): "Uniquement entre les devises suivantes : USD, CAD, GBP, " +
                        "EUR, CHF, INR, AUD";
                CurrencyConvertor.convert(100, e1,e2, this.conversion);
            } catch (AssertionError | ParseException e) {
                assertEquals("Uniquement entre les devises suivantes : USD, CAD, GBP, " +
                        "EUR, CHF, INR, AUD", e.getMessage());
                System.out.println("Monnaies : " + e1 + " et " + e2 + " -- " + e.getMessage());
            }
        });

    }

    public void testCurrency() throws ParseException {
        // Jeu de test pour les monnaies :
        // valide

        double res = CurrencyConvertor.convert(500, "USD","CAD", this.conversion);
        assertEquals(res, 672.1753188431832);
        //invalide
        String invalidCurrency = "ZENI";
        try {
            System.out.println("Conversion from USD to inexisting currency...");
            CurrencyConvertor.convert(500, "USD",invalidCurrency, this.conversion);
        }
        catch (Exception e) {
            // catch error
            System.out.println(invalidCurrency + " is " + e.getMessage());
        }
    }

    public  void testAmount(){
        // Jeu de test pour les montants :
        ArrayList<Integer> amounts = new ArrayList(Arrays.asList(-9000, -1, 0, 5000, 10000, 10001, 20000));

        for (Integer amount: amounts) {
            try {
                assert amount <= 10000 : "Seulement accepter des montants entre [0, 10000]";
                assert amount >= 0 : "Seulement accepter des montants entre [0, 10000]";
                CurrencyConvertor.convert(amount, "USD","CAD", this.conversion);
            }
            catch (AssertionError | ParseException e) {
                assertEquals(e.getMessage(), "Seulement accepter des montants entre [0, 10000]");
                System.out.println("Amount : " + amount + ", " + e.getMessage());
            }
        }
    }

}