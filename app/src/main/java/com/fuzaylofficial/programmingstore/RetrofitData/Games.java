package com.fuzaylofficial.programmingstore.RetrofitData;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Games {
    @SerializedName("pagination")
    @Expose
    public Cursor cursor;

    public static class Cursor{
        public String getCusor() { return cusor; }
        public void setCusor(String cusor) { this.cusor = cusor; }

        @SerializedName("cursor")
        @Expose
        public String cusor;
    }

    @SerializedName("data")
    List<Game> gameList;
    public List<Game> getGameList() { return gameList; }
    public void setGameList(List<Game> gameList) { this.gameList = gameList; }

    public static class Game{

        @SerializedName("box_art_url")
        @Expose
        public String art_url;

        public String getArt_url() {
            return art_url;
        }

        public void setArt_url(String art_url) {
            this.art_url = art_url;
        }
    }

}
