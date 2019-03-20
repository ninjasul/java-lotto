package lottogame.domain;

import org.junit.Test;

import static lottogame.domain.PurchaseAmount.LOTTO_PRICE;
import static org.junit.Assert.*;

public class PurchaseAmountTest {

    private PurchaseAmount purchaseAmount;

    @Test
    public void getLottoCount_for_exact_value() {
        int expected = 20;

        purchaseAmount = new PurchaseAmount(20000);

        assertEquals(expected, purchaseAmount.getLottoCount());
    }

    @Test
    public void getLottoCount_for_value_has_remainder() {
        int expected = 12;

        purchaseAmount = new PurchaseAmount(12345);

        assertEquals(expected, purchaseAmount.getLottoCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isInvalid_0() {
        new PurchaseAmount(0).getValue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void isInvalid_insufficient_amount_of_money() {
        new PurchaseAmount(LOTTO_PRICE-1).getValue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void isInvalid_exceeded_amount_of_money() {
        new PurchaseAmount(Long.MAX_VALUE).getValue();
    }

    @Test
    public void constructor_of_PurchaseAmount_for_LOTTO_PRICE() {
        long expected = LOTTO_PRICE;

        purchaseAmount = new PurchaseAmount(expected);

        assertEquals( expected, purchaseAmount.getValue());
    }

    @Test
    public void constructor_of_PurchaseAmount_PurchaseAmount_for_10_times_of_LOTTO_PRICE() {
        long expected = LOTTO_PRICE * 10;

        purchaseAmount = new PurchaseAmount(expected);

        assertEquals( expected, purchaseAmount.getValue());
    }

    @Test
    public void constructor_of_PurchaseAmount_for_maximum_value() {
        long expected = Long.MAX_VALUE-1;

        purchaseAmount = new PurchaseAmount(expected);

        assertEquals( expected, purchaseAmount.getValue());
    }
}