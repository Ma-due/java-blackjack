package main.javablackjack.view;

import main.javablackjack.domain.user.User;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하시오. (쉼표로 구분)");
        return SCANNER.nextLine();
    }

    public static String inputIntentionOf(User player) {
        System.out.println(player + "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        return SCANNER.nextLine();
    }

    public static String inputBettingMoney(User player) {
        System.out.println(player + "의 베팅 금액은?");
        return SCANNER.nextLine();
    }
}
