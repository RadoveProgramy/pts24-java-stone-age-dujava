package sk.uniba.fmph.dcs.game_board;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.EndOfGameEffect;
import sk.uniba.fmph.dcs.stone_age.InterfacePlayerBoardGameBoard;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CurrentThorwTest {

    InterfacePlayerBoardGameBoard iFace = new InterfacePlayerBoardGameBoard() {
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

    @Test
    public void initiateWoodTest() {

        Player player = new Player(new PlayerOrder(1, 1), iFace);
        CurrentThrow currentThrow = new CurrentThrow(2);

        currentThrow.initiate(player, Effect.WOOD, 2);

        assertEquals(true, currentThrow.finishUsingTools());
        assertNotNull(currentThrow.getThrowResult()); // getThrowResult returns the final value of throw
    }

    @Test
    public void initialClayTest() {
        Player player = new Player(new PlayerOrder(1, 1), iFace);
        CurrentThrow currentThrow = new CurrentThrow(0);

        currentThrow.initiate(player, Effect.CLAY, 4);
        assertTrue(currentThrow.canUseTools());
        currentThrow.useTool(3);
        assertNotEquals(2, currentThrow.getThrowResult());
    }

    @Test
    public void wrongResource() {
        Effect effect = Effect.ONE_TIME_TOOL3;
        Player player = new Player(new PlayerOrder(0, 0), iFace);
        CurrentThrow ct = new CurrentThrow(2);

        assertFalse(ct.canUseTools());
    }

    // same test would be for other resources
}
