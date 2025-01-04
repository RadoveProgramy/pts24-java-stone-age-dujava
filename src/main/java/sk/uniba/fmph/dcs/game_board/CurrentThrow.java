package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.InterfaceToolUse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// treba porozmyslat nad konstruktorom pretoze nie som si isty co tam ma byt ale asi nie toto co tam mam
// a mozno bude lepsie spravit najpr triedu ResourceSource pretoze ona pouziva tuto triedu
public class CurrentThrow implements InterfaceToolUse {

    private Effect throwsFor;
    private int throwResult;
    private int sumOfThrows;
    private int location;
    private Player player;
    private List<Integer> usedTools;
    private int huntingField = 2;
    private int forest = 3;
    private int clayMound = 4;
    private int quarry = 5;
    private int river = 6;
    private Throw t;

    public CurrentThrow(final int throwResult) {
        // this.throwsFor = throwsFor;
        this.throwResult = 0;
        this.t = new Throw();
        int[] diceThrows = Arrays.stream(t.throwDice(throwResult)).toArray();
        for (int i = 0; i < diceThrows.length; i++) {
            this.throwResult += diceThrows[i];
        }
    }

    /**
     *
     * @param player
     *            - current player
     * @param effect
     *            - effect
     * @param dices
     *            - number of dices
     */

    public void initiate(final Player player, final Effect effect, final int dices) {
        this.throwsFor = effect;
        this.player = player;
        this.usedTools = new ArrayList<>();
        int sumOfThrows = 0;
        int[] diceThrows = Arrays.stream(this.t.throwDice(dices)).toArray();
        for (int i = 0; i < diceThrows.length; i++) {
            sumOfThrows += diceThrows[i];
        }

        switch (effect) {
        case FOOD:
            this.location = huntingField;
            this.throwResult = sumOfThrows / huntingField;
            break;
        case WOOD:
            this.location = forest;
            this.throwResult = sumOfThrows / forest;
            break;
        case CLAY:
            this.location = clayMound;
            this.throwResult = sumOfThrows / clayMound;
            break;
        case STONE:
            this.location = quarry;
            this.throwResult = sumOfThrows / quarry;
            break;
        case GOLD:
            this.location = river;
            this.throwResult = sumOfThrows / river;
            break;
        default:
        }
        this.sumOfThrows = sumOfThrows;

    }

    /**
     *
     * @return - string
     */

    public String state() {
        return null;
    }

    public int getThrowResult() {
        return this.throwResult;
    }

    /**
     *
     * @param idx - value of tool
     *
     * @return
     */

    @Override
    public boolean useTool(final int idx) {
        if (!canUseTools() || !player.playerBoard().hasSufficientTools(idx)) {
            return false;
        } else {
            usedTools.add(idx);
            player.playerBoard().useTool(idx);
            this.sumOfThrows += idx;
            this.throwResult = this.sumOfThrows / this.location;
            return true;
        }
    }

    /**
     *
     * @return - whether the tool can be used on the Effect that player want
     */

    @Override
    public boolean canUseTools() {
        if (this.throwsFor.isResourceOrFood()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */

    @Override
    public boolean finishUsingTools() {
        Effect[] effects = new Effect[this.throwResult];
        for (int i = 0; i < this.throwResult; i++) {
            effects[i] = this.throwsFor;
        }
        this.player.playerBoard().giveEffect(effects);
        return true;
    }
}
