package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;

public class BuildingTile implements InterFaceFigureLocationInternal {
    public BuildingTile() {
    }

    /**
     *
     * @param player
     * @param figureCount
     *
     * @return
     */
    @Override
    public boolean placeFigures(final Player player, final int figureCount) {
        return false;
    }

    /**
     *
     * @param player
     * @param count
     *
     * @return
     */

    @Override
    public HasAction tryToPlaceFigures(final Player player, final int count) {
        return null;
    }

    /**
     *
     * @param player
     * @param inputResources
     * @param outputResources
     *
     * @return
     */

    @Override
    public ActionResult makeAction(final Player player, final Effect[] inputResources, final Effect[] outputResources) {
        return null;
    }

    /**
     *
     * @param player
     *
     * @return
     */

    @Override
    public boolean skipAction(final Player player) {
        return false;
    }

    /**
     *
     * @param player
     *
     * @return
     */

    @Override
    public HasAction tryToMakeAction(final Player player) {
        return null;
    }

    /**
     *
     * @return
     */

    @Override
    public boolean newTurn() {
        return false;
    }
}
