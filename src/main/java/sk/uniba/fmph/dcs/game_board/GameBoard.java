package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.InterfaceFigureLocation;
import sk.uniba.fmph.dcs.stone_age.InterfaceGetState;
import sk.uniba.fmph.dcs.stone_age.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoard implements InterfaceGetState {

    private final List<Player> players;
    private final Map<Location, InterfaceFigureLocation> locationMap;
    private final ToolMakerHutFields toolMakerHutFields;
    private final ResourceSource resourceSourceFood;
    private final ResourceSource resourceSourceWood;
    private final ResourceSource resourceSourceStone;
    private final ResourceSource resourceSourceClay;
    private final ResourceSource resourceSourceGold;
    private final CurrentThrow currentThrow;
    private final CivilizationCardPlace civilizationCardPlace1;
    private final CivilizationCardPlace civilizationCardPlace2;
    private final CivilizationCardPlace civilizationCardPlace3;
    private final CivilizationCardPlace civilizationCardPlace4;
    private final CivilizationCardDeck civilizationCardDeck;
    private final RewardMenu rewardMenu;
    private final BuildingTile buildingTile1;
    private final BuildingTile buildingTile2;
    private final BuildingTile buildingTile3;
    private final BuildingTile buildingTile4;

    public GameBoard(final List<Player> players, final ToolMakerHutFields toolMakerHutFields,
            final ResourceSource resourceSourceFood, final ResourceSource resourceSourceWood,
            final ResourceSource resourceSourceStone, final ResourceSource resourceSourceClay,
            final ResourceSource resourceSourceGold, final CurrentThrow currentThrow,
            final CivilizationCardPlace civilizationCardPlace1, final CivilizationCardPlace civilizationCardPlace2,
            final CivilizationCardPlace civilizationCardPlace3, final CivilizationCardPlace civilizationCardPlace4,
            final CivilizationCardDeck civilizationCardDeck, final RewardMenu rewardMenu,
            final BuildingTile buildingTile1, final BuildingTile buildingTile2, final BuildingTile buildingTile3,
            final BuildingTile buildingTile4) {

        this.locationMap = new HashMap<>();
        locationMap.put(Location.HUNTING_GROUNDS, new FigureLocationAdaptor(resourceSourceFood, players));
        locationMap.put(Location.FOREST, new FigureLocationAdaptor(resourceSourceFood, players));
        locationMap.put(Location.QUARY, new FigureLocationAdaptor(resourceSourceStone, players));
        locationMap.put(Location.CLAY_MOUND, new FigureLocationAdaptor(resourceSourceClay, players));
        locationMap.put(Location.RIVER, new FigureLocationAdaptor(resourceSourceGold, players));

        locationMap.put(Location.CIVILISATION_CARD1, new FigureLocationAdaptor(civilizationCardPlace1, players));
        locationMap.put(Location.CIVILISATION_CARD2, new FigureLocationAdaptor(civilizationCardPlace2, players));
        locationMap.put(Location.CIVILISATION_CARD3, new FigureLocationAdaptor(civilizationCardPlace3, players));
        locationMap.put(Location.CIVILISATION_CARD4, new FigureLocationAdaptor(civilizationCardPlace4, players));

        locationMap.put(Location.BUILDING_TILE1, new FigureLocationAdaptor(buildingTile1, players));
        locationMap.put(Location.BUILDING_TILE2, new FigureLocationAdaptor(buildingTile2, players));
        locationMap.put(Location.BUILDING_TILE3, new FigureLocationAdaptor(buildingTile3, players));
        locationMap.put(Location.BUILDING_TILE4, new FigureLocationAdaptor(buildingTile4, players));

        locationMap.put(Location.FIELD,
                new FigureLocationAdaptor(new PlaceOnFieldsAdaptor(toolMakerHutFields), players));
        locationMap.put(Location.TOOL_MAKER,
                new FigureLocationAdaptor(new PlaceOnFieldsAdaptor(toolMakerHutFields), players));

        this.players = players;
        this.toolMakerHutFields = toolMakerHutFields;
        this.resourceSourceFood = resourceSourceFood;
        this.resourceSourceWood = resourceSourceWood;
        this.resourceSourceStone = resourceSourceStone;
        this.resourceSourceClay = resourceSourceClay;
        this.resourceSourceGold = resourceSourceGold;
        this.currentThrow = currentThrow;
        this.civilizationCardPlace1 = civilizationCardPlace1;
        this.civilizationCardPlace2 = civilizationCardPlace2;
        this.civilizationCardPlace3 = civilizationCardPlace3;
        this.civilizationCardPlace4 = civilizationCardPlace4;
        this.civilizationCardDeck = civilizationCardDeck;
        this.rewardMenu = rewardMenu;
        this.buildingTile1 = buildingTile1;
        this.buildingTile2 = buildingTile2;
        this.buildingTile3 = buildingTile3;
        this.buildingTile4 = buildingTile4;
    }

    // All getters
    public List<Player> getPlayers() {
        return players;
    }

    public Map<Location, InterfaceFigureLocation> getLocationMap() {
        return locationMap;
    }

    public ToolMakerHutFields getToolMakerHutFields() {
        return this.toolMakerHutFields;
    }

    public ResourceSource getResourceSourceFood() {
        return this.resourceSourceFood;
    }

    public ResourceSource getResourceSourceWood() {
        return this.resourceSourceWood;
    }

    public ResourceSource getResourceSourceStone() {
        return resourceSourceStone;
    }

    public ResourceSource getResourceSourceClay() {
        return resourceSourceClay;
    }

    public ResourceSource getResourceSourceGold() {
        return resourceSourceGold;
    }

    public CurrentThrow getCurrentThrow() {
        return this.currentThrow;
    }

    public CivilizationCardPlace getCivilizationCardPlace1() {
        return this.civilizationCardPlace1;
    }

    public CivilizationCardPlace getCivilizationCardPlace2() {
        return this.civilizationCardPlace2;
    }

    public CivilizationCardPlace getCivilizationCardPlace3() {
        return this.civilizationCardPlace3;
    }

    public CivilizationCardPlace getCivilizationCardPlace4() {
        return this.civilizationCardPlace4;
    }

    public InterfaceFigureLocation getLocation(Location location) {
        return this.locationMap.get(location);
    }

    public CivilizationCardDeck getCivilizationCardDeck() {
        return this.civilizationCardDeck;
    }

    public RewardMenu getRewardMenu() {
        return this.rewardMenu;
    }

    public BuildingTile getBuildingTile1() {
        return buildingTile1;
    }

    public BuildingTile getBuildingTile2() {
        return buildingTile2;
    }

    public BuildingTile getBuildingTile3() {
        return buildingTile3;
    }

    public BuildingTile getBuildingTile4() {
        return buildingTile4;
    }

    @Override
    public String state() {
        return null;
    }
}
