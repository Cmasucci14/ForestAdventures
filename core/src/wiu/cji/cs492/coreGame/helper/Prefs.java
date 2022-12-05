package wiu.cji.cs492.coreGame.helper;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.lang.reflect.Array;


public class Prefs {

    private Preferences gameSaveData;


    public Prefs(){
        gameSaveData = Gdx.app.getPreferences("Game Save");
        gameSaveData.getInteger("level1",0);
        gameSaveData.getInteger("level2",0);
        gameSaveData.getInteger("level3",0);
        gameSaveData.getInteger("level4",0);
        gameSaveData.getInteger("level5",0);
        gameSaveData.getInteger("level6",0);
        gameSaveData.getInteger("level7",0);
        gameSaveData.getInteger("level8",0);
        gameSaveData.getInteger("level9",0);
        gameSaveData.getInteger("score1",0);
        gameSaveData.getInteger("score2",0);
        gameSaveData.getInteger("score3",0);
        gameSaveData.getInteger("score4",0);
        gameSaveData.getInteger("score5",0);
        gameSaveData.getInteger("score6",0);
        gameSaveData.getInteger("score7",0);
        gameSaveData.getInteger("score8",0);
        gameSaveData.getInteger("score9",0);

    }

    public int getScore(String Level){
        switch (Level) {
            case "MapAssets/Map1.1":
                return gameSaveData.getInteger("score1");
            case "MapAssets/Map1.2":
                return gameSaveData.getInteger("score2");
            case "MapAssets/Map1.3":
                return gameSaveData.getInteger("score3");
            case "MapAssets/Map1.4":
                return gameSaveData.getInteger("score4");
            case "MapAssets/Map1.5":
                return gameSaveData.getInteger("score5");
            case "MapAssets/Map1.6":
                return gameSaveData.getInteger("score6");
            case "MapAssets/Map1.7":
                return gameSaveData.getInteger("score7");
            case "MapAssets/Map1.8":
                return gameSaveData.getInteger("score8");
            case "MapAssets/Map1.9":
                return gameSaveData.getInteger("score9");
            default:
                Gdx.app.log("Save file Error", "Level not found");
                break;
        }
        return 0;
    }

    public void updateScore(String Level, int score){

        switch (Level) {
            case "MapAssets/Map1.1":
                if(score > gameSaveData.getInteger("score1"))
                    gameSaveData.putInteger("score1", score);
                gameSaveData.putInteger("level1", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.2":
                if(score > gameSaveData.getInteger("score2"))
                    gameSaveData.putInteger("score2", score);
                gameSaveData.putInteger("level2", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.3":
                if(score > gameSaveData.getInteger("score3"))
                    gameSaveData.putInteger("score3", score);
                gameSaveData.putInteger("level3", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.4":
                if(score > gameSaveData.getInteger("score4"))
                    gameSaveData.putInteger("score4", score);
                gameSaveData.putInteger("level4", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.5":
                if(score > gameSaveData.getInteger("score5"))
                    gameSaveData.putInteger("score5", score);
                gameSaveData.putInteger("level5", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.6":
                if(score > gameSaveData.getInteger("score6"))
                    gameSaveData.putInteger("score6", score);
                gameSaveData.putInteger("level6", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.7":
                if(score > gameSaveData.getInteger("score7"))
                    gameSaveData.putInteger("score7", score);
                gameSaveData.putInteger("level7", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.8":
                if(score > gameSaveData.getInteger("score8"))
                    gameSaveData.putInteger("score8", score);
                gameSaveData.putInteger("level8", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.9":
                if(score > gameSaveData.getInteger("score9"))
                    gameSaveData.putInteger("score9", score);
                gameSaveData.putInteger("level9", 1);
                gameSaveData.flush();
                break;
            default:
                Gdx.app.log("Save file Error", "Level not found");
                break;
        }



    }

    public void increaseLevel(String Level){

        switch (Level) {
            case "MapAssets/Map1.1":
                gameSaveData.putInteger("level1", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.2":
                gameSaveData.putInteger("level2", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.3":
                gameSaveData.putInteger("level3", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.4":
                gameSaveData.putInteger("level4", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.5":
                gameSaveData.putInteger("level5", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.6":
                gameSaveData.putInteger("level6", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.7":
                gameSaveData.putInteger("level7", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.8":
                gameSaveData.putInteger("level8", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.9":
                gameSaveData.putInteger("level9", 1);
                gameSaveData.flush();
                break;
            default:
                Gdx.app.log("Save file Error", "Level not found");
                break;
        }


    }

    public Preferences getGameSaveData() {
        return gameSaveData;
    }
}
