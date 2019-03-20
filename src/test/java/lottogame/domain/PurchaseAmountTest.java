package lottogame.domain;

import org.junit.Test;

import static lottogame.domain.PurchaseAmount.LOTTO_PRICE;
import static org.junit.Assert.*;

public class PurchaseAmountTest {

    private PurchaseAmount purchaseAmount;

    @Test
    public void getLottoCount() {
        int expected = 20;

        purchaseAmount = new PurchaseAmount(20000);

        assertEquals(expected, purchaseAmount.getLottoCount());
    }

    @Test
    public void isInvalid_0() {
        assertTrue(PurchaseAmount.isInvalid(0));
    }

    @Test
    public void isInvalid_insufficient_amount_of_money() {
        assertTrue(PurchaseAmount.isInvalid(LOTTO_PRICE-1));
    }

    @Test
    public void isInvalid_exceeded_amount_of_money() {
        assertTrue(PurchaseAmount.isInvalid(Long.MAX_VALUE));
    }

    @Test
    public void isInvalid_valid_amount_of_money() {
        assertFalse(PurchaseAmount.isInvalid(10000));
        assertFalse(PurchaseAmount.isInvalid(14000));
        assertFalse(PurchaseAmount.isInvalid(Long.MAX_VALUE-1));
    }
}