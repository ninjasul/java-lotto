package lottogame.domain;

import lottogame.validator.InputValidatable;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static lottogame.domain.LottoNumber.MAXIMUM_LOTTO_NUMBER;
import static lottogame.domain.LottoNumber.MINIMUM_LOTTO_NUMBER;

/**
 * 로또 1게임
 */
public class LottoNumberPackage implements InputValidatable<Set<Integer>> {

    public static final int LOTTO_GAME_SIZE = 6;

    private final LinkedHashSet<LottoNumber> numbers;

    public LottoNumberPackage(Set<Integer> lottoNumbers) {
        validate(lottoNumbers);
        numbers = getGameNumbers(lottoNumbers);
    }

    private static LinkedHashSet<LottoNumber> getGameNumbers(Set<Integer> gameNumbers) {
        return gameNumbers
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    Set<LottoNumber> getNumbers() {
        return numbers;
    }

    public MatchStatus getMatchStatus(WinningInfo winningNumbers) {
        return new MatchStatus(
            getMatchedCount(winningNumbers.getNumbers()),
            isBonusNumberMatched(winningNumbers.getBonusNumber())
        );
    }

    int getMatchedCount(LottoNumberPackage targetNumbers) {
        if( targetNumbers == null ||
            targetNumbers.getNumbers() == null ||
            targetNumbers.getNumbers().size() <= 0 ) {
            return 0;
        }

        return (int)targetNumbers.getNumbers()
                .stream()
                .filter(this::contains)
                .count();
    }

    boolean isBonusNumberMatched(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public boolean contains(LottoNumber targetNumber) {
        return Optional.ofNullable(targetNumber)
                .filter(numbers::contains)
                .isPresent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumberPackage that = (LottoNumberPackage) o;
        return numbers.equals(that.numbers);
    }

    @Override
    public boolean isInvalid(Set<Integer> target) {
        return target == null || target.size() != LOTTO_GAME_SIZE;
    }

    @Override
    public String getInvalidMessage() {
        return MINIMUM_LOTTO_NUMBER + "에서 " + MAXIMUM_LOTTO_NUMBER + "까지 중복없이 숫자 " + LOTTO_GAME_SIZE + "개를 입력하세요.";
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        return String.valueOf(numbers);
    }
}