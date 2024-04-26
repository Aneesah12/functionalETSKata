package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class PlayerServiceTest {
    private static final Player PLAYER_ONE = new Player("Clare", 25, 25);
    private static final Player PLAYER_TWO = new Player("Roger", 28, 22);
    private static final Player PLAYER_THREE = new Player("Steven", 24, 23);
    private static final Player PLAYER_FOUR = new Player("Josh", 50, 32);
    private static final Player PLAYER_FIVE = new Player("Agatha", 43, 36);

    private final PlayerService playerService = new PlayerService();

    List<Player> footballTeam = new ArrayList<>();

    @Before
    public void init() {
        footballTeam = new ArrayList<>(
                Arrays.asList(
                        PLAYER_ONE,
                        PLAYER_TWO,
                        PLAYER_THREE,
                        PLAYER_FOUR,
                        PLAYER_FIVE
                ));
    }

    @Test
    public void shouldSortTheScoresIntoAscendingOrder() {
        //given
        Comparator<Player> scoreRanking = Comparator.comparingInt(Player::getScore);

        List<Player> expectedTopScoresSorted = new ArrayList<>();
        expectedTopScoresSorted.add(PLAYER_THREE);
        expectedTopScoresSorted.add(PLAYER_ONE);
        expectedTopScoresSorted.add(PLAYER_TWO);
        expectedTopScoresSorted.add(PLAYER_FIVE);
        expectedTopScoresSorted.add(PLAYER_FOUR);

        //when
        List<Player> sortedScores = playerService.getSortedScores(footballTeam, scoreRanking);

        //then
        assertThat(sortedScores).isEqualTo(expectedTopScoresSorted);
    }

    @Test
    public void shouldReturnTheTopThreeScores() {
        //given
        Comparator<Player> scoreRanking = Comparator.comparingInt(Player::getScore);

        List<Player> expectedTopTwoScoresSorted = new ArrayList<>();
        expectedTopTwoScoresSorted.add(PLAYER_FOUR);
        expectedTopTwoScoresSorted.add(PLAYER_FIVE);
        expectedTopTwoScoresSorted.add(PLAYER_TWO);

        //when
        List<Player> topTwoScores = playerService.topTwoScores(footballTeam, scoreRanking);

        //then
        assertThat(topTwoScores).isEqualTo(expectedTopTwoScoresSorted);
    }

    @Test
    public void shouldReturnTheTopPlayerScore() {
        //given
        Comparator<Player> scoreRanking = Comparator.comparingInt(Player::getScore);

        //when
        Player topScore = playerService.topScore(footballTeam, scoreRanking);

        //then
        assertThat(topScore).isEqualTo(PLAYER_FOUR);
    }

    @Test
    public void shouldReturnThePlayersWhoAreUnder25() {
        //given
        BiPredicate<Integer, Integer> checkAge = (p1, p2) -> p1 < p2;

        List<Player> expectedUnder25s = new ArrayList<>();
        expectedUnder25s.add(PLAYER_TWO);
        expectedUnder25s.add(PLAYER_THREE);

        //when
        List<Player> under25s = playerService.under25s(footballTeam, checkAge);

        //then
        assertThat(under25s).isEqualTo(expectedUnder25s);
    }

    @Test
    public void shouldReturnAListOfTheExpectedPrizeMoney() {
        //given
        BiFunction<Integer, Integer, Integer> calcPrizeMoney = (prize, multiplier) -> prize * multiplier;

        List<Integer> expectedPrizeMoney = new ArrayList<>();
        expectedPrizeMoney.add(1250);
        expectedPrizeMoney.add(1400);
        expectedPrizeMoney.add(1200);
        expectedPrizeMoney.add(2500);
        expectedPrizeMoney.add(2150);

        //when
        List<Integer> actualPrizeMoney = playerService.calculatePrizeMoney(footballTeam, calcPrizeMoney);

        //then
        assertThat(actualPrizeMoney).isEqualTo(expectedPrizeMoney);
    }

}