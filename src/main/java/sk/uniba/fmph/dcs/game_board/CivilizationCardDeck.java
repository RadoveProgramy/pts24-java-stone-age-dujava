package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.CivilisationCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CivilizationCardDeck {

    private List<CivilisationCard> civilisationCards;
    private int civilizationCardCounter;

    public CivilizationCardDeck(final CivilisationCard[] cards) {
        this.civilisationCards = new ArrayList<>();
        this.civilizationCardCounter = 0;
        for (int i = 0; i < cards.length; i++) {
            this.civilisationCards.addLast(cards[i]);
            this.civilizationCardCounter++;
        }
    }

    /**
     *
     * @return - CivilizationCard that is on the top or empty card deck
     */

    public Optional<CivilisationCard> getTop() {
        if (!this.civilisationCards.isEmpty()) {
            CivilisationCard top = this.civilisationCards.getLast();
            this.civilisationCards.removeLast();
            this.civilizationCardCounter--;
            return Optional.of(top);
        } else {
            return Optional.empty();
        }
    }

    /**
     *
     * @return - number of cards in the deck
     */

    public String state() {
        return String.valueOf("Number of cards: " + this.civilizationCardCounter);
    }
}
