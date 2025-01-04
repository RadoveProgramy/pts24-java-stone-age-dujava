package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.List;

public class GameBoardFactory {

    public GameBoard createGameBoard(List<Player> players, CivilizationCardDeck civilizationCardDeck,
            List<BuildingTile> buildingTiles, CurrentThrow currentThrow) {

        int figureColor;
        int value = players.get(0).playerOrder().getPlayers();
        if (value == 2) {
            figureColor = 1;
        } else if (value == 3) {
            figureColor = 2;
        } else {
            figureColor = 4;
        }
        RewardMenu rewardMenu = new RewardMenu(null, new Player[] { players.get(0) });
        ResourceSource food = new ResourceSource("FOOD", Effect.FOOD, Integer.MAX_VALUE, figureColor,
                new PlayerOrder[] { players.get(0).playerOrder() });
        ResourceSource wood = new ResourceSource("WOOD", Effect.WOOD, 7, figureColor,
                new PlayerOrder[] { players.get(0).playerOrder() });
        ResourceSource stone = new ResourceSource("STONE", Effect.STONE, 7, figureColor,
                new PlayerOrder[] { players.get(0).playerOrder() });
        ResourceSource clay = new ResourceSource("CLAY", Effect.CLAY, 7, figureColor,
                new PlayerOrder[] { players.get(0).playerOrder() });
        ResourceSource gold = new ResourceSource("GOLD", Effect.GOLD, 7, figureColor,
                new PlayerOrder[] { players.get(0).playerOrder() });


        CivilizationCardPlace civilizationCardPlace1 = new CivilizationCardPlace(1, civilizationCardDeck, rewardMenu, currentThrow);
        CivilizationCardPlace civilizationCardPlace2 = new CivilizationCardPlace(2, civilizationCardDeck, rewardMenu, currentThrow);
        CivilizationCardPlace civilizationCardPlace3 = new CivilizationCardPlace(3, civilizationCardDeck, rewardMenu, currentThrow);
        CivilizationCardPlace civilizationCardPlace4 = new CivilizationCardPlace(4, civilizationCardDeck, rewardMenu, currentThrow);

        BuildingTile buildingTile1 = null;
        BuildingTile buildingTile2 = null;
        BuildingTile buildingTile3 = null;
        BuildingTile buildingTile4 = null;
        int size = buildingTiles.size();
        if(size > 3) buildingTile4 = buildingTiles.get(3);
        if(size > 2) buildingTile3 = buildingTiles.get(2);
        if(size > 1) buildingTile2 = buildingTiles.get(1);
        if(size > 0) buildingTile1 = buildingTiles.get(0);
        ToolMakerHutFields toolMakerHutFields = new ToolMakerHutFields(players.size());


        GameBoard gameBoard = new GameBoard(players, toolMakerHutFields, food, wood, stone, clay, gold, currentThrow, civilizationCardPlace1, civilizationCardPlace2, civilizationCardPlace3, civilizationCardPlace4, civilizationCardDeck, rewardMenu, buildingTile1, buildingTile2, buildingTile3, buildingTile4);

        return gameBoard;
    }
}