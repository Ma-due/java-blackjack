package main.javablackjack.domain.controller;

import main.javablackjack.domain.BettingMoney;
import main.javablackjack.domain.PlayerIntentionType;
import main.javablackjack.domain.PlayerMoneys;
import main.javablackjack.domain.card.Deck;
import main.javablackjack.domain.user.Player;
import main.javablackjack.domain.user.User;
import main.javablackjack.domain.user.Users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.javablackjack.view.InputView.inputBettingMoney;
import static main.javablackjack.view.InputView.inputIntentionOf;
import static main.javablackjack.view.OutputView.*;

public class BlackjackController {
    public static void proceedInitialPhase(Users users, Deck deck) {
        printInitialDistribution(users.getPlayers());

        for (User user : users) {
            user.proceedInitalPhase(deck);
            printInitialStatus(user);
        }
    }

    public static PlayerMoneys getBettingMoney(List<Player> players) {
        Map<Player, Integer> result = new HashMap<>();
        players.forEach(player -> result.put(player, BettingMoney.of(inputBettingMoney(player)).intValue()));
        return new PlayerMoneys(result);
    }

    public static void proceedGame(List<Player> players, User dealer, Deck deck) {
        players.forEach(player -> proceedPhaseOf(player, deck));
        proceedPhaseOf(dealer, deck);
    }

    private static void proceedPhaseOf(Player player, Deck deck) {
        while (player.canDrawMore() && PlayerIntentionType.of(inputIntentionOf(player)).isWantDraw()) {
            player.receive(deck.pop());
            printCardsStatusOf(player);
        }
    }

    private static void proceedPhaseOf(User dealer, Deck deck) {
        while (dealer.canDrawMore())
            dealer.receive(deck.pop());
        printDealerDrawing();
    }
}
