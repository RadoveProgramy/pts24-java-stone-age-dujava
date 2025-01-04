package sk.uniba.fmph.dcs.game_board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.uniba.fmph.dcs.stone_age.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResourceSourceTest {

    @Test
    public void placeFiguresTest() {
        InterfacePlayerBoardGameBoard IF = new InterfacePlayerBoardGameBoard() {
            @Override
            public void giveEffect(Effect[] stuff) {

            }

            @Override
            public void giveEndOfGameEffect(EndOfGameEffect[] stuff) {

            }

            @Override
            public boolean takeResources(Effect[] stuff) {
                return false;
            }

            @Override
            public boolean giveFigure() {
                return false;
            }

            @Override
            public boolean takeFigures(int count) {
                return false;
            }

            @Override
            public boolean hasFigures(int count) {
                return false;
            }

            @Override
            public boolean hasSufficientTools(int goal) {
                return false;
            }

            @Override
            public Optional<Integer> useTool(int idx) {
                return Optional.empty();
            }
        };
        PlayerOrder playerOrder = new PlayerOrder(1, 1);
        Player player = new Player(playerOrder, IF);
        ResourceSource source = new ResourceSource("aa", Effect.WOOD, 4, 4, new PlayerOrder[] { playerOrder });

        assertTrue(source.placeFigures(player, 3));
        assertEquals(HasAction.NO_ACTION_POSSIBLE, source.tryToPlaceFigures(player, 10));
    }
}
/*
 *
 * private ResourceSource resourceSource; private PlayerOrder[] figures; private Map<PlayerOrder, Integer> figuresCount;
 * private Player player;
 *
 *
 * @BeforeEach public void setUp() { InterfacePlayerBoardGameBoard iFaceFalse = new InterfacePlayerBoardGameBoard() {
 *
 * @Override public void giveEffect(Effect[] stuff) {
 *
 * }
 *
 * @Override public void giveEndOfGameEffect(EndOfGameEffect[] stuff) {
 *
 * }
 *
 * @Override public boolean takeResources(Effect[] stuff) { return false; }
 *
 * @Override public boolean giveFigure() { return false; }
 *
 * @Override public boolean takeFigures(int count) { return false; }
 *
 * @Override public boolean hasFigures(int count) { return false; }
 *
 * @Override public boolean hasSufficientTools(int goal) { return false; }
 *
 * @Override public Optional<Integer> useTool(int idx) { return Optional.empty(); } };
 *
 * this.figuresCount = new HashMap<>(); this.player = new Player(new PlayerOrder(0,4), iFaceFalse); this.figures = new
 * PlayerOrder[2]; this.resourceSource = new ResourceSource("aaa", Effect.WOOD, 7, 4, this.figures); }
 *
 * @Test public void tryToPlaceFiguresTest() { assertEquals(this.resourceSource.tryToPlaceFigures(player, 10),
 * HasAction.NO_ACTION_POSSIBLE); assertEquals(this.resourceSource.tryToPlaceFigures(player, 0),
 * HasAction.WAITING_FOR_PLAYER_ACTION); assertEquals(this.resourceSource.tryToPlaceFigures(player, 2),
 * HasAction.WAITING_FOR_PLAYER_ACTION); assertEquals(this.resourceSource.tryToPlaceFigures(player, 8),
 * HasAction.NO_ACTION_POSSIBLE); }
 *
 * @Test public void placeFiguresTest(){ assertEquals(this.resourceSource.placeFigures(player, 1), true);
 * assertEquals(this.resourceSource.placeFigures(player, 10), false);
 *
 * } }
 *
 */
