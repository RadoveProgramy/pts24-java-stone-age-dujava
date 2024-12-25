package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.*;

import java.util.Optional;

public class CivilizationCardPlace implements InterFaceFigureLocationInternal {

    private PlayerOrder[] figures;
    private int requiredResources;
    private CivilizationCardDeck civilizationCardDeck;
    private Optional<CivilisationCard> civilisationCard;
    private RewardMenu rewardMenu;

    public CivilizationCardPlace(final int requiredResources, final PlayerOrder[] playerOrder,
                                 CivilizationCardDeck civilizationCardDeck, RewardMenu rewardMenu) {
        this.requiredResources = requiredResources;
        this.figures = playerOrder;
        this.civilizationCardDeck = civilizationCardDeck;
        this.civilisationCard = civilizationCardDeck.getTop();
        this.rewardMenu = rewardMenu;
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
        if(tryToPlaceFigures(player, figureCount) != HasAction.NO_ACTION_POSSIBLE){
            int length = figures.length;
            figures[length] = player.playerOrder();
            player.playerBoard().takeFigures(1);
        }
        return false;
    }

    @Override
    public HasAction tryToPlaceFigures(Player player, int count) {
        if(figures.length > 0 || count != 1 || !player.playerBoard().hasFigures(count)){
            return HasAction.NO_ACTION_POSSIBLE;
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    @Override
    public ActionResult makeAction(Player player, Effect[] inputResources, Effect[] outputResources) {

    }

    @Override
    public boolean skipAction(Player player) {
        for (int i = 0; i < figures.length; i++) {
            if(figures[i] == player.playerOrder()){
                figures[i] = null;
                player.playerBoard().takeFigures(-1);
                return true;
            }
        }
        return false;

    }

    @Override
    public HasAction tryToMakeAction(Player player) {
        if(this.civilisationCard != null){
            for (int i = 0; i < figures.length; i++) {
                if(figures[i] == player.playerOrder()){
                    return HasAction.NO_ACTION_POSSIBLE;
                }
            }
            return HasAction.WAITING_FOR_PLAYER_ACTION;
        }
        return HasAction.NO_ACTION_POSSIBLE;
    }

    @Override
    public boolean newTurn() {
        return false;
    }
}
