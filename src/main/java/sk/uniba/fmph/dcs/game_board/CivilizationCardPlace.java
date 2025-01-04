package sk.uniba.fmph.dcs.game_board;



import sk.uniba.fmph.dcs.stone_age.*;

import java.util.*;

public class CivilizationCardPlace implements InterFaceFigureLocationInternal {

    private List<PlayerOrder> figures;
    private int requiredResources;
    private CivilizationCardDeck civilizationCardDeck;
    private Optional<CivilisationCard> civilisationCard;
    private RewardMenu rewardMenu;
    private CurrentThrow currentThrow;

    public CivilizationCardPlace(final int requiredResources,
            CivilizationCardDeck civilizationCardDeck, RewardMenu rewardMenu, CurrentThrow currentThrow) {
        this.requiredResources = requiredResources;
        //this.figures = Arrays.asList(playerOrder);
        this.civilizationCardDeck = civilizationCardDeck;
        this.civilisationCard = civilizationCardDeck.getTop();
        this.rewardMenu = rewardMenu;
        this.currentThrow = currentThrow;
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
        if (tryToPlaceFigures(player, figureCount) != HasAction.NO_ACTION_POSSIBLE) {
            player.playerBoard().takeFigures(1);
            figures.add(player.playerOrder());
            return true;
        }
        return false;
    }

    @Override
    public HasAction tryToPlaceFigures(Player player, int count) {
        if (figures.size() > 0 || count != 1 || !player.playerBoard().hasFigures(count)) {
            return HasAction.NO_ACTION_POSSIBLE;
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    @Override
    public ActionResult makeAction(Player player, Effect[] inputResources, Effect[] outputResources) {
        if(inputResources.length >= this.requiredResources && tryToMakeAction(player) != HasAction.NO_ACTION_POSSIBLE){
            ActionResult result = ActionResult.ACTION_DONE;
            Effect[] toUse = new Effect[this.requiredResources];
            int counter = 0;
            // counting resources that player need to use
            for(Effect effect : inputResources){
                if(counter < requiredResources){
                    toUse[counter] = effect;
                    counter++;
                }
                else{
                    break;
                }
            }

            boolean toTake = player.playerBoard().takeResources(toUse);
            if(!toTake){
                return ActionResult.FAILURE;
            }

            player.playerBoard().takeFigures(-1);
            figures.remove(player.playerOrder());

            for(EndOfGameEffect effect : civilisationCard.get().endOfGameEffect()){
                player.playerBoard().giveEndOfGameEffect(new EndOfGameEffect[]{effect});
            }

            for (ImmediateEffect effect : civilisationCard.get().immediateEffect()){
                EvaluateCivilisationCardImmediateEffect evaluateEffect;
                if(effect == ImmediateEffect.FOOD){
                    evaluateEffect = new GetSomethingFixed(new Effect[]{Effect.FOOD});
                    evaluateEffect.performEffect(player, Effect.FOOD);
                }
                else if(effect == ImmediateEffect.WOOD){
                    evaluateEffect = new GetSomethingFixed(new Effect[]{Effect.WOOD});
                    evaluateEffect.performEffect(player, Effect.WOOD);
                }
                else if(effect == ImmediateEffect.STONE){
                    evaluateEffect = new GetSomethingFixed(new Effect[]{Effect.STONE});
                    evaluateEffect.performEffect(player, Effect.STONE);
                }
                else if(effect == ImmediateEffect.CLAY){
                    evaluateEffect = new GetSomethingFixed(new Effect[]{Effect.CLAY});
                    evaluateEffect.performEffect(player, Effect.CLAY);
                }
                else if(effect == ImmediateEffect.GOLD){
                    evaluateEffect = new GetSomethingFixed(new Effect[]{Effect.GOLD});
                    evaluateEffect.performEffect(player, Effect.GOLD);
                }
                else if(effect == ImmediateEffect.THROW_WOOD){
                    evaluateEffect = new GetSomethingThrow(Effect.WOOD, this.currentThrow);
                    evaluateEffect.performEffect(player, Effect.WOOD);
                    result = ActionResult.ACTION_DONE_WAIT_FOR_TOOL_USE;
                }
                else if(effect == ImmediateEffect.THROW_STONE){
                    evaluateEffect = new GetSomethingThrow(Effect.STONE, this.currentThrow);
                    evaluateEffect.performEffect(player, Effect.STONE);
                    result = ActionResult.ACTION_DONE_WAIT_FOR_TOOL_USE;
                }
                else if(effect == ImmediateEffect.THROW_CLAY){
                    evaluateEffect = new GetSomethingThrow(Effect.CLAY, this.currentThrow);
                    evaluateEffect.performEffect(player, Effect.CLAY);
                    result = ActionResult.ACTION_DONE_WAIT_FOR_TOOL_USE;
                }
                else if(effect == ImmediateEffect.THROW_GOLD){
                    evaluateEffect = new GetSomethingThrow(Effect.GOLD, this.currentThrow);
                    evaluateEffect.performEffect(player, Effect.GOLD);
                    result = ActionResult.ACTION_DONE_WAIT_FOR_TOOL_USE;
                }
                else if(effect == ImmediateEffect.CARD){
                    evaluateEffect = new GetCard(this.civilizationCardDeck, this.currentThrow);
                    evaluateEffect.performEffect(player, null);
                }
                else if(effect == ImmediateEffect.ARBITRARY_RESOURCE){
                    evaluateEffect = new GetChoice(inputResources);
                    for(Effect e : outputResources){
                        evaluateEffect.performEffect(player, e);
                    }
                }
                else if(effect == ImmediateEffect.ALL_PLAYERS_TAKE_REWARD){
                    result = ActionResult.ACTION_DONE_ALL_PLAYERS_TAKE_A_REWARD;
                }
            }
            return result;
        }
        else{
            return ActionResult.FAILURE;
        }
    }

    @Override
    public boolean skipAction(Player player) {
        if(figures.contains(player.playerOrder())){
            player.playerBoard().takeFigures(-1);
            figures.remove(player.playerOrder());
            return true;
        }
        return false;

    }

    @Override
    public HasAction tryToMakeAction(Player player) {
        if(civilisationCard != null){
            if(figures.contains(player.playerOrder())){
                return HasAction.NO_ACTION_POSSIBLE;
            }
            else{
                return HasAction.WAITING_FOR_PLAYER_ACTION;
            }
        }
        return HasAction.NO_ACTION_POSSIBLE;
    }

    @Override
    public boolean newTurn() {
        return false;
    }
}
