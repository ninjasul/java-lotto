package lottogame;

import lottogame.domain.*;
import lottogame.view.InputView;
import lottogame.view.ResultView;

public class ConsoleApplicationLauncher {

    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = InputView.getPurchaseAmount();
        LottoTicket lottoTicket = new LottoTicket(purchaseAmount);
        ResultView.showPurchasedResult(lottoTicket);

        LottoGame winningNumbers = InputView.getWinningNumbers();
        LottoNumber bonusNumber = InputView.getBonusNumber();
        LottoResult lottoResult = new LottoResult(lottoTicket, new WinningNumbers(winningNumbers, bonusNumber));
        ResultView.showWinningResult(lottoResult);
    }
}