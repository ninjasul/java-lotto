package lottogame.domain;

import org.assertj.core.util.Streams;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static lottogame.domain.PurchaseAmount.LOTTO_PRICE;
import static org.junit.Assert.*;

public class LottoResultTest {

    private LottoResult lottoResult;
    private Set<Integer> winningNumbers = getSet(Arrays.asList(40, 41, 42, 43, 44, 45));
    private int bonusNumber = 7;

    @Test
    public void test_MISS_rank() {
        List<LottoGame> gameNumbers = Arrays.asList(
                getLottoGame(Arrays.asList(1, 2, 3, 4, 5, 6)),
                getLottoGame(Arrays.asList(1, 2, 3, 4, bonusNumber, 40)),
                getLottoGame(Arrays.asList(1, 2, bonusNumber, 4, 40, 41))
        );

        lottoResult = getLottoResult(gameNumbers.size() * LOTTO_PRICE, gameNumbers, winningNumbers, bonusNumber);
        Map<Rank, Long> winningCountPerRank = lottoResult.getWinningCountPerRank();
        System.out.println(winningCountPerRank);

        assertEquals(gameNumbers.size(), winningCountPerRank.get(Rank.MISS).longValue());
        assertEquals(gameNumbers.size(), lottoResult.getWinningCount(Rank.MISS));
        assertEquals(0, lottoResult.getTotalWinningMoney());
    }

    @Test
    public void getWinningCountPerRank_FIFTH() {
        List<LottoGame> gameNumbers = Arrays.asList(
            getLottoGame(Arrays.asList(40, 41, 42, 4, 5, 6)),
            getLottoGame(Arrays.asList(bonusNumber, 41, 42, 43, 5, 6)),
            getLottoGame(Arrays.asList(1, 2, 42, 43, 44, 6)),
            getLottoGame(Arrays.asList(1, 2, bonusNumber, 43, 44, 45))
        );

        lottoResult = getLottoResult(gameNumbers.size() * LOTTO_PRICE, gameNumbers, winningNumbers, bonusNumber);
        Map<Rank, Long> winningCountPerRank = lottoResult.getWinningCountPerRank();
        System.out.println(winningCountPerRank);

        assertEquals(gameNumbers.size(), winningCountPerRank.get(Rank.FIFTH).longValue());
        assertEquals(gameNumbers.size(), lottoResult.getWinningCount(Rank.FIFTH));
        assertEquals(Rank.FIFTH.getWinningMoney() * gameNumbers.size(), lottoResult.getTotalWinningMoney());
    }

    @Test
    public void getWinningCountPerRank_FOURTH() {
        List<LottoGame> gameNumbers = Arrays.asList(
            getLottoGame(Arrays.asList(40, 41, 42, 43, 5, 6)),
            getLottoGame(Arrays.asList(bonusNumber, 41, 42, 43, 44, 6)),
            getLottoGame(Arrays.asList(1, 2, 42, 43, 44, 45)),
            getLottoGame(Arrays.asList(40, 41, 3, 43, 44, bonusNumber)),
            getLottoGame(Arrays.asList(40, 41, 3, 4, 44, 45))
        );

        lottoResult = getLottoResult(gameNumbers.size() * LOTTO_PRICE, gameNumbers, winningNumbers, bonusNumber);
        Map<Rank, Long> winningCountPerRank = lottoResult.getWinningCountPerRank();
        System.out.println(winningCountPerRank);

        assertEquals(gameNumbers.size(), winningCountPerRank.get(Rank.FOURTH).longValue());
        assertEquals(gameNumbers.size(), lottoResult.getWinningCount(Rank.FOURTH));
        assertEquals(Rank.FOURTH.getWinningMoney() * gameNumbers.size(), lottoResult.getTotalWinningMoney());
    }

    @Test
    public void getWinningCountPerRank_THIRD() {
        List<LottoGame> gameNumbers = Arrays.asList(
            getLottoGame(Arrays.asList(1, 41, 42, 43, 44, 45)),
            getLottoGame(Arrays.asList(40, 2, 42, 43, 44, 45)),
            getLottoGame(Arrays.asList(40, 41, 3, 43, 44, 45)),
            getLottoGame(Arrays.asList(40, 41, 42, 4, 44, 45)),
            getLottoGame(Arrays.asList(40, 41, 42, 43, 5, 45)),
            getLottoGame(Arrays.asList(40, 41, 42, 43, 44, 6))
        );

        lottoResult = getLottoResult(gameNumbers.size() * LOTTO_PRICE, gameNumbers, winningNumbers, bonusNumber);
        Map<Rank, Long> winningCountPerRank = lottoResult.getWinningCountPerRank();
        System.out.println(winningCountPerRank);

        assertEquals(gameNumbers.size(), winningCountPerRank.get(Rank.THIRD).longValue());
        assertEquals(gameNumbers.size(), lottoResult.getWinningCount(Rank.THIRD));
        assertEquals(Rank.THIRD.getWinningMoney() * gameNumbers.size(), lottoResult.getTotalWinningMoney());
    }

    @Test
    public void getWinningCountPerRank_SECOND() {
        List<LottoGame> gameNumbers = Arrays.asList(
                getLottoGame(Arrays.asList(bonusNumber, 41, 42, 43, 44, 45)),
                getLottoGame(Arrays.asList(40, bonusNumber, 42, 43, 44, 45)),
                getLottoGame(Arrays.asList(40, 41, bonusNumber, 43, 44, 45)),
                getLottoGame(Arrays.asList(40, 41, 42, bonusNumber, 44, 45)),
                getLottoGame(Arrays.asList(40, 41, 42, 43, bonusNumber, 45)),
                getLottoGame(Arrays.asList(40, 41, 42, 43, 44, bonusNumber))
        );

        lottoResult = getLottoResult(gameNumbers.size() * LOTTO_PRICE, gameNumbers, winningNumbers, bonusNumber);
        Map<Rank, Long> winningCountPerRank = lottoResult.getWinningCountPerRank();
        System.out.println(winningCountPerRank);

        assertEquals(gameNumbers.size(), winningCountPerRank.get(Rank.SECOND).longValue());
        assertEquals(gameNumbers.size(), lottoResult.getWinningCount(Rank.SECOND));
        assertEquals(Rank.SECOND.getWinningMoney() * gameNumbers.size(), lottoResult.getTotalWinningMoney());
    }

    @Test
    public void getWinningCountPerRank_FIRST() {
        List<LottoGame> gameNumbers = Arrays.asList(
            getLottoGame(Arrays.asList(40, 41, 42, 43, 44, 45))
        );

        lottoResult = getLottoResult(gameNumbers.size() * LOTTO_PRICE, gameNumbers, winningNumbers, bonusNumber);        Map<Rank, Long> winningCountPerRank = lottoResult.getWinningCountPerRank();
        System.out.println(winningCountPerRank);

        assertEquals(gameNumbers.size(), winningCountPerRank.get(Rank.FIRST).longValue());
        assertEquals(gameNumbers.size(), lottoResult.getWinningCount(Rank.FIRST));
        assertEquals(Rank.FIRST.getWinningMoney() * gameNumbers.size(), lottoResult.getTotalWinningMoney());
    }

    @Test
    public void getWinningCountPerRank_MIXED() {
        List<LottoGame> gameNumbers = Arrays.asList(
            // MISS
            getLottoGame(Arrays.asList(1, 2, 3, 4, 5, 6)),
            getLottoGame(Arrays.asList(1, 2, 3, 4, 5, 40)),
            getLottoGame(Arrays.asList(1, 2, 3, 4, 40, 41)),

            // FIFTH
            getLottoGame(Arrays.asList(40, 41, 42, 4, 5, 6)),
            getLottoGame(Arrays.asList(1, 41, 42, 43, 5, 6)),
            getLottoGame(Arrays.asList(1, 2, 42, 43, 44, 6)),
            getLottoGame(Arrays.asList(1, 2, 3, 43, 44, 45)),

            // FOURTH
            getLottoGame(Arrays.asList(40, 41, 42, 43, 5, 6)),
            getLottoGame(Arrays.asList(1, 41, 42, 43, 44, 6)),
            getLottoGame(Arrays.asList(1, 2, 42, 43, 44, 45)),
            getLottoGame(Arrays.asList(40, 41, 3, 43, 44, 6)),
            getLottoGame(Arrays.asList(40, 41, 3, 4, 44, 45)),

            // THIRD
            getLottoGame(Arrays.asList(1, 41, 42, 43, 44, 45)),
            getLottoGame(Arrays.asList(40, 2, 42, 43, 44, 45)),
            getLottoGame(Arrays.asList(40, 41, 3, 43, 44, 45)),
            getLottoGame(Arrays.asList(40, 41, 42, 4, 44, 45)),
            getLottoGame(Arrays.asList(40, 41, 42, 43, 5, 45)),
            getLottoGame(Arrays.asList(40, 41, 42, 43, 44, 6)),

            // SECOND
            getLottoGame(Arrays.asList(bonusNumber, 41, 42, 43, 44, 45)),
            getLottoGame(Arrays.asList(40, bonusNumber, 42, 43, 44, 45)),
            getLottoGame(Arrays.asList(40, 41, bonusNumber, 43, 44, 45)),
            getLottoGame(Arrays.asList(40, 41, 42, bonusNumber, 44, 45)),
            getLottoGame(Arrays.asList(40, 41, 42, 43, bonusNumber, 45)),
            getLottoGame(Arrays.asList(40, 41, 42, 43, 44, bonusNumber)),

            // FIRST
            getLottoGame(Arrays.asList(40, 41, 42, 43, 44, 45))
        );

        lottoResult = getLottoResult(gameNumbers.size() * LOTTO_PRICE, gameNumbers, winningNumbers, bonusNumber);        Map<Rank, Long> winningCountPerRank = lottoResult.getWinningCountPerRank();
        System.out.println(winningCountPerRank);

        assertEquals(3, winningCountPerRank.get(Rank.MISS).longValue());
        assertEquals(3, lottoResult.getWinningCount(Rank.MISS));

        assertEquals(4, winningCountPerRank.get(Rank.FIFTH).longValue());
        assertEquals(4, lottoResult.getWinningCount(Rank.FIFTH));

        assertEquals(5, winningCountPerRank.get(Rank.FOURTH).longValue());
        assertEquals(5, lottoResult.getWinningCount(Rank.FOURTH));

        assertEquals(6, winningCountPerRank.get(Rank.THIRD).longValue());
        assertEquals(6, lottoResult.getWinningCount(Rank.THIRD));

        assertEquals(6, winningCountPerRank.get(Rank.SECOND).longValue());
        assertEquals(6, lottoResult.getWinningCount(Rank.SECOND));

        assertEquals(1, winningCountPerRank.get(Rank.FIRST).longValue());
        assertEquals(1, lottoResult.getWinningCount(Rank.FIRST));

        long expected = Rank.MISS.getWinningMoney() * 3 +
                        Rank.FIFTH.getWinningMoney() * 4 +
                        Rank.FOURTH.getWinningMoney() * 5 +
                        Rank.THIRD.getWinningMoney() * 6 +
                        Rank.SECOND.getWinningMoney() * 6 +
                        Rank.FIRST.getWinningMoney() * 1;

        assertEquals(expected, lottoResult.getTotalWinningMoney());
        System.out.println(lottoResult.getWinningResultString());
    }

    private LottoGame getLottoGame(List<Integer> integers) {
        return new LottoGame(getSet(integers));
    }

    private Set<Integer> getSet(List<Integer> integers) {
        return integers.stream()
                .collect(Collectors.toSet());
    }

    private LottoResult getLottoResult(long purchaseAmount,
                                       List<LottoGame> automaticNumbers,
                                       Set<Integer> winningNumbers,
                                       int bonusNumber) {

        PurchaseAmount purchaseAmountObj = new PurchaseAmount(purchaseAmount);
        WinningNumbers winningNumbersObj = new WinningNumbers(new LottoGame(winningNumbers), new LottoNumber(bonusNumber));
        return new LottoResult(new LottoTicket(purchaseAmountObj, automaticNumbers), winningNumbersObj);
    }
}