package lottogame.view;

import lottogame.domain.LottoGame;
import lottogame.domain.LottoNumber;
import lottogame.domain.PurchaseAmount;
import lottogame.util.StringUtils;

import java.util.Scanner;

public class InputView {
    static final String NUMBER_DELIMETER = ",";

    private static Scanner scanner = new Scanner(System.in);

    public InputView(Scanner scanner) {
        InputView.scanner = scanner;
    }

    public static PurchaseAmount getPurchaseAmount() {
        showPurchaseAmountInputMessage();
        return new PurchaseAmount(StringUtils.parseLong(getInputLine()));
    }

    private static void showPurchaseAmountInputMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static LottoGame getWinningNumbers() {
        showWinningNumbersInputMessage();
        return new LottoGame(getInputLine().split(NUMBER_DELIMETER));
    }

    private static void showWinningNumbersInputMessage() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public static LottoNumber getBonusNumber() {
        showBonusNumberInputMessage();
        return new LottoNumber(StringUtils.parseInt(getInputLine()));
    }

    private static void showBonusNumberInputMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    static String getInputLine() {
        return StringUtils.removeWhitespace(scanner.nextLine());
    }
}