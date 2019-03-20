package lottogame.domain;

import lottogame.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static lottogame.domain.LottoNumber.MAXIMUM_LOTTO_NUMBER;
import static lottogame.domain.LottoNumber.MINIMUM_LOTTO_NUMBER;

/**
 * 로또 1게임
 */
public class LottoGame {

    public static final int LOTTO_GAME_SIZE = 6;

    private final LinkedHashSet<LottoNumber> gameNumbers;

    public LottoGame(String[] lottoNumbers) {
        if (isInvalid(lottoNumbers)) {
            throw new IllegalArgumentException(getInvalidArgumentExceptionMessage());
        }

        gameNumbers = getGameNumbers(lottoNumbers);
    }

    public LottoGame(Set<Integer> lottoNumbers) {
        if (isInvalid(lottoNumbers)) {
            throw new IllegalArgumentException(getInvalidArgumentExceptionMessage());
        }

        gameNumbers = getGameNumbers(lottoNumbers);
    }

    private String getInvalidArgumentExceptionMessage() {
        return MINIMUM_LOTTO_NUMBER + "에서 " + MAXIMUM_LOTTO_NUMBER + "까지 중복없이 숫자 " + LOTTO_GAME_SIZE + "개를 입력하세요.";
    }

    static boolean isInvalid(String[] lottoNumbers) {
        return lottoNumbers == null ||
                lottoNumbers.length != LOTTO_GAME_SIZE ||
                isInvalid(new LinkedHashSet(Arrays.asList(lottoNumbers)));
    }

    static boolean isInvalid(Set<Integer> lottoNumbers) {
        return lottoNumbers == null || lottoNumbers.size() != LOTTO_GAME_SIZE;
    }

    static LinkedHashSet<LottoNumber> getGameNumbers(String[] gameNumbers) {
        return getGameNumbers(
                Optional.ofNullable(gameNumbers)
                        .map(StringUtils::parseIntegerSet)
                        .orElse(Collections.emptySet())
        );
    }

    static LinkedHashSet<LottoNumber> getGameNumbers(Set<Integer> gameNumbers) {
        return Optional.ofNullable(gameNumbers)
                .orElse(Collections.emptySet())
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    Set<LottoNumber> getGameNumbers() {
        return gameNumbers;
    }

    public MatchStatus getMatchStatus(WinningNumbers winningNumbers) {
        return new MatchStatus(
            getMatchedCount(winningNumbers.getNumbers()),
            isBonusNumberMatched(winningNumbers.getBonusNumber())
        );
    }

    int getMatchedCount(LottoGame targetNumbers) {
        return (int) Optional.ofNullable(targetNumbers)
                .map(LottoGame::getGameNumbers)
                .orElse(Collections.emptySet())
                .stream()
                .filter(this::contains)
                .count();
    }

    boolean isBonusNumberMatched(LottoNumber bonusNumber) {
        return gameNumbers.contains(bonusNumber);
    }

    public boolean contains(LottoNumber targetNumber) {
        return Optional.ofNullable(targetNumber)
                .filter(gameNumbers::contains)
                .isPresent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoGame that = (LottoGame) o;
        return gameNumbers.equals(that.gameNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameNumbers);
    }

    @Override
    public String toString() {
        return String.valueOf(gameNumbers);
    }
}