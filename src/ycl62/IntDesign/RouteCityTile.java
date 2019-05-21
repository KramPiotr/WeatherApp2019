package ycl62.IntDesign;

import tiles.Tile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RouteCityTile extends Tile implements Comparable<RouteCityTile> {
    private Tile tile;
    private int cityID;
    private LocalDate start, end;
    
    public RouteCityTile(RouteScene parent, int cityID, LocalDate start, LocalDate end){
        super(parent);
        this.cityID = cityID;
        this.start = start;
        this.end = end;
        if(start.equals(end)){
            tile = new RouteSingleTile(parent, cityID, start.format(DateTimeFormatter.ofPattern("d MMM")));
        } else {
            tile = new RouteMultiTile(parent, cityID, start, end);
        }
        this.getChildren().add(tile);
    }
    
    @Override
    public void update(){
        tile.update();
    }
    
    @Override
    public int compareTo(RouteCityTile o){
        int d = start.compareTo(o.start);
        if(d == 0){
            int e = end.compareTo(o.end);
            if(e == 0){
                return cityID - o.cityID;
            }
            return e;
        }
        return d;
    }
}
