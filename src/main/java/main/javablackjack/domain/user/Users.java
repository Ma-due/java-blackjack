package main.javablackjack.domain.user;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Users implements Iterable<User> {
    private static final int MAX_PLAYERS_COUNT = 8;
    private static final int MIN_PLAYERS_COUNT = 2;
    private static final String SPLIT_DELIMITER = ",";

    private final List<User> users;

    private Users(List<User> players, User dealer) {
        players.add(dealer);
        if (players.size() > MAX_PLAYERS_COUNT) {
            throw new IllegalArgumentException("블랙잭의 최대 인원은 8명입니다.");
        }
        if (players.size() < MIN_PLAYERS_COUNT) {
            throw new IllegalArgumentException("블랙잭의 최소 인원은 2명입니다.");
        }
        this.users = players;
    }

    public static Users of(List<User> players, User dealer) {
        return new Users(players, dealer);
    }

    public static Users of(String playerNames, User dealer) {
        return of(Arrays.stream(playerNames.split(SPLIT_DELIMITER))
                .map(String::trim)
                .map(Player::new)
                .collect(Collectors.toList()), dealer);
    }

    public List<Player> getPlayers() {
        return users.stream()
                .filter(user -> user.getClass() == Player.class)
                .map(user -> (Player) user)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
}
