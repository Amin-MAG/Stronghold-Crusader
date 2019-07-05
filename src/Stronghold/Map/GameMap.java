package Stronghold.Map;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

import Stronghold.Map.Tile.DesertTile;
import Stronghold.Map.Tile.Tile;
import Stronghold.ResourceManager;
import org.json.simple.JSONArray;

public class GameMap {


    public String mapName;
    Tile[][] gameBoard;

    public GameMap(String mapName) {


        this.mapName = mapName;

        Map mapJsonFile = ResourceManager.getJson(mapName);


        JSONArray boardSizeStr = (JSONArray) mapJsonFile.get("size");
        int[] boardSize = new int[] {
                Integer.parseInt(boardSizeStr.get(0).toString()),
                Integer.parseInt(boardSizeStr.get(1).toString()),
        };

        JSONArray boardArrStr = (JSONArray) mapJsonFile.get("board");
        int[][] boardArr = new int[boardSize[0]][boardSize[1]];
        for (int i = 0; i < boardSize[0]; i++) for (int j = 0; j < boardSize[1]; j++)  boardArr[i][j] = Integer.parseInt(boardArrStr.get(0).toString().charAt(j) + "");

//        for (int i = 0; i < boardSize[0]; i++) for (int j = 0; j < boardSize[1]; j++){
//
//            switch (boardArr[i][j]) {
//                case 1:
//                    gameBoard[i][j] = new DesertTile();
//                    break;
//                default:
//                    break;
//            }
//
//        }



    }


}
