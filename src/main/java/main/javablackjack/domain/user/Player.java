package main.javablackjack.domain.user;

import main.javablackjack.domain.card.Card;
import main.javablackjack.domain.card.Cards;
import main.javablackjack.domain.user.strategy.draw.PlayerDrawStrategy;

import java.util.List;

public class Player extends User{

    private final String name;

    public Player(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름이 빈 문자열입니다.");
        }
        this.name = name;
        drawStrategy = new PlayerDrawStrategy();
    }

    public Player(String name, List<Card> cards) {
        this(name);
        hands = new Cards(cards);
    }

    @Override
    public Cards openInitialCards() {
        return hands;
    }

    @Override
    public String toString() {
        return name;
    }
}
