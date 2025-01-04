package sk.uniba.fmph.dcs.game_board;

import org.checkerframework.checker.units.qual.A;
import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.HashMap;
import java.util.Map;

public class ResourceSource implements InterFaceFigureLocationInternal {

    private String name;
    private Effect resource;
    private int maxFigures;
    private int maxfiguresPerPlayer;
    private PlayerOrder[] figures;
    private Map<PlayerOrder, Integer> figuresCounts;

    public ResourceSource(final String name, final Effect resource, final int maxFigures, final int maxFigureColour,
            final PlayerOrder[] figures) {
        this.name = name;
        this.resource = resource;
        this.maxFigures = maxFigures;
        this.maxfiguresPerPlayer = maxFigureColour;
        this.figures = figures;
        this.figuresCounts = new HashMap<>();
    }

    public String state() {
        return null;
    }

    @Override
    public boolean placeFigures(final Player player, final int figureCount) {
        if (tryToPlaceFigures(player, figureCount).equals(HasAction.NO_ACTION_POSSIBLE)) {
            return false;
        } else {
            if (!this.figuresCounts.containsKey(player.playerOrder())) {
                this.figuresCounts.put(player.playerOrder(), figureCount);
            } else {
                this.figuresCounts.put(player.playerOrder(),
                        this.figuresCounts.get(player.playerOrder()) + figureCount);
            }
            player.playerBoard().takeFigures(figureCount);
            return true;
        }
    }

    /**
     *
     * @param player
     *            - current player
     *
     * @return - false if player want to put more figures than possible or player hasn't number of figures
     */

    @Override
    public HasAction tryToPlaceFigures(final Player player, final int count) {
        if ((figures.length + count > maxFigures) || (player.playerBoard().hasFigures(count))) {
            return HasAction.NO_ACTION_POSSIBLE;
        } else {
            for (int i = 0; i < figures.length; i++) {
                if (figures[i] == player.playerOrder()) {
                    return HasAction.WAITING_FOR_PLAYER_ACTION;
                }
            }
            if (figures.length >= maxfiguresPerPlayer) {
                return HasAction.NO_ACTION_POSSIBLE;
            } else {
                return HasAction.WAITING_FOR_PLAYER_ACTION;
            }
        }
    }

    @Override
    public ActionResult makeAction(final Player player, final Effect[] inputResources, final Effect[] outputResources) {
        return null;
    }

    @Override
    public boolean skipAction(Player player) {
        for (int i = 0; i < this.figures.length; i++) {
            if (this.figures[i] == player.playerOrder()) {
                return false;
            }
        }
        player.playerBoard().takeFigures(this.figuresCounts.get(player.playerOrder()) * -1);
        this.figuresCounts.remove(player.playerOrder());
        return true;
    }

    @Override
    public HasAction tryToMakeAction(Player player) {
        for (int i = 0; i < this.figures.length; i++) {
            if (this.figures[i] == player.playerOrder()) {
                return HasAction.NO_ACTION_POSSIBLE;
            }
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    @Override
    public boolean newTurn() {
        int length = this.figures.length;
        this.figures = new PlayerOrder[length];
        return false;
    }
}
