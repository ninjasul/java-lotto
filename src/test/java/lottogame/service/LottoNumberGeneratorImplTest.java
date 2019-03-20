package lottogame.service;

import lottogame.domain.LottoNumber;
import org.junit.Test;

import java.util.Set;

import static lottogame.domain.LottoNumberPackage.LOTTO_GAME_SIZE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LottoNumberGeneratorImplTest {

    private LottoNumberGeneratorImpl lottoNumberGenerator;

    @Test
    public void getLottoNumbers() {
        lottoNumberGenerator = new LottoNumberGeneratorImpl();

        Set<Integer> lottoNumbers = lottoNumberGenerator.getLottoNumbers();
        System.out.println(lottoNumbers);

        assertEquals(LOTTO_GAME_SIZE, lottoNumbers.size());
        for(int curNumber : lottoNumbers) {
            assertFalse(LottoNumber.isInvalid(curNumber));
        }
    }
}