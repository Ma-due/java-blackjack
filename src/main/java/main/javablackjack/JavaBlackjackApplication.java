package main.javablackjack;

import main.javablackjack.domain.PlayerMoneys;
import main.javablackjack.domain.card.CardRepository;
import main.javablackjack.domain.card.Deck;
import main.javablackjack.domain.controller.BlackjackController;
import main.javablackjack.domain.user.Dealer;
import main.javablackjack.domain.user.User;
import main.javablackjack.domain.user.Users;

import static main.javablackjack.view.InputView.*;
import static main.javablackjack.view.OutputView.*;

public class JavaBlackjackApplication {
    public static void main(String[] args) {
        User dealer = new Dealer();
        Deck deck = Deck.of(CardRepository.toList());
        Users users = Users.of(inputPlayerNames(), dealer);
        PlayerMoneys playerMoneys = BlackjackController.getBettingMoney(users.getPlayers());

        BlackjackController.proceedInitialPhase(users, deck);

        if(dealer.isNotBlackJack()) {
            BlackjackController.proceedGame(users.getPlayers(), dealer, deck);
        }

        printResultStatus(users);
        printResultProfit(playerMoneys, dealer);
    }
}
