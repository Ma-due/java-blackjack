package main.javablackjack.view;

import main.javablackjack.domain.PlayerMoneys;
import main.javablackjack.domain.card.Card;
import main.javablackjack.domain.card.Cards;
import main.javablackjack.domain.user.Player;
import main.javablackjack.domain.user.User;
import main.javablackjack.domain.user.Users;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";

    public static void printInitialDistribution(List<Player> players) {
        System.out.println("딜러와 " + players.stream()
                .map(Player::toString)
                .collect(Collectors.joining(DELIMITER)) +
                "에게 2장의 카드를 나눠줬습니다.");
    }

    public static void printInitialStatus(User user) {
        System.out.println(user + ": " + user.openInitialCards().toList().stream()
                .map(Card::toString)
                .collect(Collectors.joining(DELIMITER)));
    }

    public static void printCardsStatusOf(Player player) {
        Cards cards = player.openAllCards();
        System.out.println(player + "카드: " +
                cards.toList().stream()
                        .map(Card::toString)
                        .collect(Collectors.joining(DELIMITER)));
    }

    public static void printDealerDrawing() {
        emptyLine();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResultStatus(Users users) {
        for (User user : users) {
            Cards cards = user.openAllCards();
            System.out.println(user + "카드: " +
                    cards.toList().stream()
                            .map(Card::toString)
                            .collect(Collectors.joining(DELIMITER)) +
                    " - 결과" + cards.getPoint());
        }
    }

    public static void printResultProfit(PlayerMoneys playerMoneys, User dealer) {
        Map<User, Double> totalPrizes = playerMoneys.getTotalPrizes(dealer);
        emptyLine();
        System.out.println("## 최종 수익");
        totalPrizes.forEach(((user, money) -> System.out.println(user + ": " + money)));
    }

    private static void emptyLine() {
        System.out.println();
    }
}
