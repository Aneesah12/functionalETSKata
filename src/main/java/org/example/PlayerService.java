package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class PlayerService {

    public List<Player> getSortedScores(List<Player> footballTeam, Comparator<Player> scoreRanking) {
        return footballTeam.stream()
                .sorted(scoreRanking)
                .toList();
    }

    public List<Player> topTwoScores(List<Player> footballTeam, Comparator<Player> scoreRanking) {
        return footballTeam.stream()
                .sorted(scoreRanking.reversed())
                .limit(3)
                .toList();
    }

    public Player topScore(List<Player> footballTeam, Comparator<Player> scoreRanking) {
        return footballTeam.stream()
                .max(scoreRanking)
                .orElseThrow(RuntimeException::new);
    }

    public List<Player> under25s(List<Player> footballTeam, BiPredicate<Integer, Integer> checkAge) {
        //takeWhile() until age is 25?
        return footballTeam.stream()
                .filter(player -> checkAge.test(player.getAge(), 25))
                .toList();
    }

    public List<Integer> calculatePrizeMoney(List<Player> footballTeam, BiFunction<Integer, Integer, Integer> calcPrizeMoney) {
        return footballTeam.stream()
                .map(player -> calcPrizeMoney.apply(player.getScore(), 50))
                .toList();
    }
}
