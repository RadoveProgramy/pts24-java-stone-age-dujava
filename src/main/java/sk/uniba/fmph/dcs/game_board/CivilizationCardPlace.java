package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

public class CivilizationCardPlace implements InterFaceFigureLocationInternal{

    private PlayerOrder figures;
    private int requiredResources;

    public CivilizationCardPlace(final int requiredResources, final PlayerOrder figures) {
        this.figures = figures;
        this.requiredResources = requiredResources;
    }

    /**
     *
     * @return - string
     */

    public String state() {
        return "a";
    }

    @Override
    public boolean placeFigures(Player player, int figureCount) {
        return false;
    }

    @Override
    public HasAction tryToPlaceFigures(Player player, int count) {
        return null;
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
