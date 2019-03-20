package lottogame.domain;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lottogame.domain.Rank.*;
import static org.junit.Assert.*;

public class RankTest {

    @Test
    public void valueOf() {
        List<Integer> invalidMatchedCounts = getRangedNumbers(-1, 2);
        invalidMatchedCounts.add(7);

        for(int curMatchedCount : invalidMatchedCounts) {
            assertEquals(MISS, Rank.valueOf(getMatchStatus(curMatchedCount, false)));
            assertEquals(MISS, Rank.valueOf(getMatchStatus(curMatchedCount, true)));
        }

        assertEquals(FIFTH, Rank.valueOf(getMatchStatus(3, false)));
        assertEquals(FIFTH, Rank.valueOf(getMatchStatus(3, true)));
        assertEquals(FOURTH, Rank.valueOf(getMatchStatus(4, false)));
        assertEquals(FOURTH, Rank.valueOf(getMatchStatus(4, true)));
        assertEquals(THIRD, Rank.valueOf(getMatchStatus(5, false)));
        assertEquals(SECOND, Rank.valueOf(getMatchStatus(5, true)));
        assertEquals(FIRST, Rank.valueOf(getMatchStatus(6, false)));
    }

    private MatchStatus getMatchStatus(int matchedCount, boolean bonusNumberMatched) {
        return new MatchStatus(matchedCount, bonusNumberMatched);
    }

    private List<Integer> getRangedNumbers(int from, int to) {
        return IntStream.rangeClosed(from, to)
                .boxed()
                .collect(Collectors.toList());
    }
}