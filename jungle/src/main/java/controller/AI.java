package controller;

import model.Chessboard;
import model.Go;
import model.PlayerColor;

import java.util.ArrayList;
import java.util.Collections;

public class AI {
    private Level level;
    private Chessboard chessboard;

    public AI(Chessboard chessboard,Level level){
        this.chessboard = chessboard;
        this.level = level;
    }
    public Go AIGo(PlayerColor color){
        if (level == Level.AI_Simple){
            return Go1(color);
    }
        if (level == Level.AI_Mid){
            return Go2(color);
        }
        if (level == Level.AI_LittleHard){
            return Go3(color);
        }return null;
    }
    public Go Go1(PlayerColor color){
        ArrayList<Go> gos = chessboard.getLegalGo(color);
        if (gos.size()>0){
            double num = 0;
            num = Math.random()*gos.size();
            return gos.get((int) num);
        }return null;
    }
    public Go Go2(PlayerColor color){
        ArrayList<Go> gos = chessboard.achieveAI2(color);
        for (int i = 0; i < gos.size()-1; i++) {
            for (int j = 0; j < gos.size()-1-i; j++) {
                if (gos.get(j).getValue()<gos.get(j+1).getValue())
                    Collections.swap(gos,j,j+1);
            }
        }
        for (Go go: gos){
            System.out.print(go.getValue()+" ");
        }
        System.out.println();
        if (gos.size()>0){
            int m = gos.get(0).getValue();
            int count = 0;
            for (Go go:gos){
                if (go.getValue() == m){
                    count++;
                }else {
                    break;
                }
            }
            return gos.get((int) (Math.random()*count));
        }
        return null;
    }
    public Go Go3(PlayerColor color) {
        ArrayList<Go> gos = chessboard.achieveAI3(color);
        for (int i = 0; i < gos.size()-1; i++) {
            for (int j = 0; j < gos.size()-1-i; j++) {
                if (gos.get(j).getValue()<gos.get(j+1).getValue())
                    Collections.swap(gos,j,j+1);
            }
        }
        for (Go go: gos){
            System.out.print(go.getValue()+" ");
        }
        System.out.println();
        if (gos.size()>0){
            int m = gos.get(0).getValue();
            int count = 0;
            for (Go go:gos){
                if (go.getValue() == m){
                    count++;
                }else {
                    break;
                }
            }
            return gos.get((int) (Math.random()*count));
        }
        return null;
    }
}
