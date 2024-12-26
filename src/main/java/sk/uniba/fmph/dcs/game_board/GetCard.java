package sk.uniba.fmph.dcs.game_board;

import org.apache.commons.collections4.Get;
import sk.uniba.fmph.dcs.stone_age.Effect;

public class GetCard implements EvaluateCivilisationCardImmediateEffect{

    private CivilizationCardDeck civilizationCardDeck;
    private CurrentThrow currentThrow;

    public GetCard(CivilizationCardDeck civilizationCardDeck, CurrentThrow currentThrow){
        this.civilizationCardDeck = civilizationCardDeck;
        this.currentThrow = currentThrow;
    }

    @Override
    public Boolean performEffect(Player player, Effect choice) {
        return null;
    }
}
