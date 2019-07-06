package Stronghold.Map;

import java.util.Map;

import Stronghold.Map.Tile.DesertTile;
import Stronghold.Map.Tile.GrassTile;
import Stronghold.Map.Tile.Tile;
import Stronghold.ResourceManager;
import org.json.simple.JSONArray;

public class GameMap {


    public String mapName;
    public Tile[][] gameBoard;

    public GameMap(String mapName) {



        Map mapJsonFile = ResourceManager.getJson(mapName);


        JSONArray boardSizeStr = (JSONArray) mapJsonFile.get("size");
        int[] boardSize = new int[] {
                Integer.parseInt(boardSizeStr.get(0).toString()),
                Integer.parseInt(boardSizeStr.get(1).toString()),
        };

        JSONArray boardArrStr = (JSONArray) mapJsonFile.get("board");
        int[][] boardArr = new int[boardSize[0]][boardSize[1]];
        for (int i = 0; i < boardSize[0]; i++) for (int j = 0; j < boardSize[1]; j++)  boardArr[i][j] = Integer.parseInt(boardArrStr.get(i).toString().charAt(j) + "");


        this.mapName = mapName;
        this.gameBoard = new Tile[boardSize[0]][boardSize[1]];


        int halfI = boardSize[0]/2;
        int halfJ = boardSize[1]/2;

        /*

            1 DESERT
            2 GRASS


         */

        for (int i = -halfI; i < halfI; i++) for (int j = -halfJ; j < halfJ; j++){

            switch (boardArr[i+halfI][j+halfJ]) {

                case 1:
                    gameBoard[i+halfI][j+halfJ] = new DesertTile(new int[] {350*i+175, 350*j+175});
                    break;
                case 2:
                    gameBoard[i+halfI][j+halfJ] = new GrassTile(new int[] {350*i+175, 350*j+175});
                default:
                    break;

            }

        }


//        for (int i = 0; i < boardSize[0]; i++){
//
//            for (int j = 0; j < boardSize[1]; j++){
//
//                System.out.print( boardArr[i][j] + " ");
//
//            }
//
//            System.out.println();
//
//        }


    }


}
