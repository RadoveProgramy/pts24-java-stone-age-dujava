package sk.uniba.fmph.dcs.game_board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.uniba.fmph.dcs.stone_age.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceSourceTest {

    private ResourceSource resourceSource;
    private PlayerOrder playerOrder1;
    private PlayerOrder playerOrder2;
    private PlayerOrder[] playerOrders;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp(){
        InterfacePlayerBoardGameBoard iFaceFalse = new InterfacePlayerBoardGameBoard() {
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
        InterfacePlayerBoardGameBoard iFaceTrue = new InterfacePlayerBoardGameBoard() {
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
                return true;
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
        this.player1 = new Player(playerOrder1, iFaceFalse);
        this.player2 = new Player(playerOrder2, iFaceTrue);
        this.playerOrder1 = new PlayerOrder(0,2);
        this.playerOrder2 = new PlayerOrder(1,2);
        this.playerOrders = new PlayerOrder[2];
        this.playerOrders[0] = this.playerOrder1;
        this.playerOrders[1] = this.playerOrder2;
        this.resourceSource = new ResourceSource("aaa", Effect.WOOD, 7, 4, this.playerOrders);
    }


    @Test
    public void tryToPlaceFiguresTest(){
        assertEquals(this.resourceSource.tryToPlaceFigures(player1, 10), HasAction.NO_ACTION_POSSIBLE);
        assertEquals(this.resourceSource.tryToPlaceFigures(player1, 0), HasAction.WAITING_FOR_PLAYER_ACTION);
        assertEquals(this.resourceSource.tryToPlaceFigures(player2, 2), HasAction.WAITING_FOR_PLAYER_ACTION);
        assertEquals(this.resourceSource.tryToPlaceFigures(player2, 8), HasAction.NO_ACTION_POSSIBLE);
    }
}
