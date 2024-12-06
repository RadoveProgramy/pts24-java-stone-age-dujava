package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

public class ResourceSource implements InterFaceFigureLocationInternal {

    private String name;
    private Effect resource;
    private int maxFigures;
    private int maxfiguresPerPlayer;
    private PlayerOrder[] figures;

    public ResourceSource(final String name, final Effect resource, final int maxFigures, final int maxFigureColour,
            final PlayerOrder[] figures) {
        this.name = name;
        this.resource = resource;
        this.maxFigures = maxFigures;
        this.maxfiguresPerPlayer = maxFigureColour;
        this.figures = figures;
    }

    public String state() {
        return null;
    }

    @Override
    public boolean placeFigures(Player player, int figureCount) {
        return false;
    }

    /**
     *
     * @param player
     *            - current player
     *
     * @return - false if player want to put more figures than possible or player hasn't number of figures
     */

    @Override
    public HasAction tryToPlaceFigures(Player player, int count) {
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
    public ActionResult makeAction(Player player, Effect[] inputResources, Effect[] outputResources) {
        return null;
    }

    @Override
    public boolean skipAction(Player player) {
        return false;
    }

    @Override
    public HasAction tryToMakeAction(Player player) {
        return null;
    }

    @Override
    public boolean newTurn() {
        return false;
    }
}
