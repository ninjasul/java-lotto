package lottogame.domain;

public class WinningNumbers {

    private final LottoGame numbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoGame numbers, LottoNumber bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoGame getNumbers() {
        return numbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}