package lottogame.domain;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lottogame.domain.LottoGame.LOTTO_GAME_SIZE;
import static lottogame.domain.LottoNumber.MAXIMUM_LOTTO_NUMBER;
import static lottogame.domain.LottoNumber.MINIMUM_LOTTO_NUMBER;
import static org.junit.Assert.*;

public class LottoGameTest {

    @Test
    public void isInvalid_for_string_array() {
        assertTrue(LottoGame.isInvalid((String[])null));
        assertTrue(LottoGame.isInvalid(new String[]{}));
        assertTrue(LottoGame.isInvalid(new String[]{"1", "2", "3", "4", "5"}));
        assertTrue(LottoGame.isInvalid(new String[]{"1", "2", "3", "4", "5", "6", "7"}));
        assertTrue(LottoGame.isInvalid(new String[]{"1", "2", "2", "3", "4", "5"}));
        assertFalse(LottoGame.isInvalid(new String[]{"1", "2", "3", "4", "5", "6"}));
    }

    @Test
    public void isInvalid_for_integer_set() {
        assertTrue(LottoGame.isInvalid((Set<Integer>)null));
        assertTrue(LottoGame.isInvalid(Collections.emptySet()));
        assertTrue(LottoGame.isInvalid(new HashSet(Arrays.asList(1, 2, 3, 4, 5))));
        assertTrue(LottoGame.isInvalid(new HashSet(Arrays.asList(1, 2, 3, 4, 5, 6, 7))));
        assertFalse(LottoGame.isInvalid(new HashSet(Arrays.asList(1, 2, 3, 4, 5, 6))));
    }

    @Test
    public void getGameNumbers_for_empty_string_array() {
        Set<LottoNumber> expected = Collections.emptySet();

        Set<LottoNumber> actualForNull = LottoGame.getGameNumbers((String[])null);
        Set<LottoNumber> actualForEmpty = LottoGame.getGameNumbers(new String[]{});
        Set<LottoNumber> actualForEmpty2 = LottoGame.getGameNumbers(new String[]{"", "", "", "", "", ""});

        assertEquals(expected, actualForNull);
        assertEquals(expected, actualForEmpty);
        assertEquals(expected, actualForEmpty2);
    }

    @Test
    public void getGameNumbers_for_string_array() {
        Set<LottoNumber> expected = new HashSet(
            Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
            )
        );

        Set<LottoNumber> actual = LottoGame.getGameNumbers(new String[]{"1", "2", "3", "4", "5", "6"});

        assertEquals(expected, actual);
    }

    @Test
    public void getGameNumbers_for_empty_integer_set() {
        Set<LottoNumber> expected = Collections.emptySet();

        Set<LottoNumber> actualForNull = LottoGame.getGameNumbers((Set<Integer>)null);
        Set<LottoNumber> actualForEmpty = LottoGame.getGameNumbers(Collections.emptySet());

        assertEquals(expected, actualForNull);
        assertEquals(expected, actualForEmpty);
    }

    @Test
    public void getGameNumbers_for_integer_set() {
        Set<LottoNumber> expected = new HashSet(
            Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
            )
        );

        Set<LottoNumber> actual = LottoGame.getGameNumbers(new HashSet(Arrays.asList(1, 2, 3, 4, 5, 6)));

        assertEquals(expected, actual);
    }

    @Test
    public void getMatchedCount() {
        Set<Integer> gameNumbers = getHashSet(Arrays.asList(1, 2, 3, 4, 5, 6));
        Set<Integer> targetNumbers0 = getHashSet(Arrays.asList(40, 41, 42, 43, 44, 45));
        Set<Integer> targetNumbers1 = getHashSet(Arrays.asList(1, 41, 42, 43, 44, 45));
        Set<Integer> targetNumbers2 = getHashSet(Arrays.asList(1, 2, 42, 43, 44, 45));
        Set<Integer> targetNumbers3 = getHashSet(Arrays.asList(40, 41, 3, 4, 5, 45));
        Set<Integer> targetNumbers4 = getHashSet(Arrays.asList(40, 2, 3, 43, 5, 6));
        Set<Integer> targetNumbers5 = getHashSet(Arrays.asList(1, 2, 42, 4, 5, 6));
        Set<Integer> targetNumbers6 = getHashSet(Arrays.asList(1, 2, 3, 4, 5, 6));

        LottoGame game = new LottoGame(gameNumbers);
        LottoGame targetGame0 = new LottoGame(targetNumbers0);
        LottoGame targetGame1 = new LottoGame(targetNumbers1);
        LottoGame targetGame2 = new LottoGame(targetNumbers2);
        LottoGame targetGame3 = new LottoGame(targetNumbers3);
        LottoGame targetGame4 = new LottoGame(targetNumbers4);
        LottoGame targetGame5 = new LottoGame(targetNumbers5);
        LottoGame targetGame6 = new LottoGame(targetNumbers6);

        assertEquals(0, game.getMatchedCount(targetGame0));
        assertEquals(1, game.getMatchedCount(targetGame1));
        assertEquals(2, game.getMatchedCount(targetGame2));
        assertEquals(3, game.getMatchedCount(targetGame3));
        assertEquals(4, game.getMatchedCount(targetGame4));
        assertEquals(5, game.getMatchedCount(targetGame5));
        assertEquals(6, game.getMatchedCount(targetGame6));
    }

    private Set<Integer> getHashSet(List<Integer> list) {
        return new HashSet(list);
    }

    @Test
    public void contains() {
        Set<Integer> gameNumbers = getRangedNumbers(MINIMUM_LOTTO_NUMBER, LOTTO_GAME_SIZE);

        LottoGame game = new LottoGame(gameNumbers);

        for(int curTargetNumber : gameNumbers) {
            assertTrue(game.contains(new LottoNumber(curTargetNumber)));
        }

        Set<Integer> uncontainedTargetNumbers = getRangedNumbers(LOTTO_GAME_SIZE + 1, MAXIMUM_LOTTO_NUMBER);

        for(int curTargetNumber : uncontainedTargetNumbers) {
            assertFalse(game.contains(new LottoNumber(curTargetNumber)));
        }
    }

    private Set<Integer> getRangedNumbers(int from, int to) {
        return IntStream.rangeClosed(from, to)
                .boxed()
                .collect(Collectors.toSet());
    }
}