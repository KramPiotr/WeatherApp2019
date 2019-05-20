/*
package ycl62.IntDesign;

import tiles.Tile;

import java.time.LocalDate;

public class RouteCityTile extends Tile implements Comparable<RouteCityTile> {
    private final double WIDTH = 600.0, HEIGHT = 200.0;
    private Tile tile;
    private String cityName;
    private LocalDate start, end;
    
    public RouteCityTile(RouteScene parent, String cityName, LocalDate start, LocalDate end){
        super(parent);
        this.cityName = cityName;
        this.start = start;
        this.end = end;
        if(start.equals(end)){
            //tile = new RouteSingleTile(parent);
        } else {
            System.out.println("graph");
            tile = new RouteMultiTile(parent, cityName, start, end);
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
                return cityName.compareTo(o.cityName);
            }
            return e;
        }
        return d;
    }
}
*/
