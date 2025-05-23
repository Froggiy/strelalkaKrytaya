package packet.maybyNBp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Hero {
    Texture texture;
    float x,y,vx,vy;
    public Rectangle hitBox;
    public Circle range;
    public String weapon;
    public Texture weaponTexture;
    public Texture ammoTexture;
    public Texture staffTexture;
    int weaponUses;


    public Hero(float x, float y) {
        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;
        weaponUses = 0;
        texture = new Texture("1a.png");
        hitBox = new Rectangle(x,y,16,16);
        range = new Circle(x-8,y-8,120);
        weapon = "no";
        weaponTexture = new Texture("sword.png");
        ammoTexture = new Texture("ammo.png");
        staffTexture = new Texture("staff.png");
    }

    public boolean isTileSolid(TiledMap map) {
        // Get the collision layer
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);

        // Convert world coordinates to tile coordinates
        int tileX = (int) (x / layer.getTileWidth());
        int tileY = (int) (y / layer.getTileHeight());

        // Get the tile cell
        TiledMapTileLayer.Cell cell = layer.getCell(tileX, tileY);

        if (cell != null) {
            StaticTiledMapTile tile = (StaticTiledMapTile) cell.getTile();
            if (tile.getProperties().containsKey("solid")) {
                return true; // Tile is solid
            }
        }
        return true; // Tile is walkable
    }
    public void moveY(){
        y+=vy;
        hitBox.y=y;
        range.y=y-52;
    }
    public void moveX(){
        x+=vx;
        hitBox.x=x;
        range.x=x-52;
    }


    public void stop(){
        vx=0;
        vy=0;
    }
    public void touch(Vector3 t){
        vx = (t.x-x)/50;
        vy = (t.y-y)/50;
    }

    public boolean isHit(Rectangle rect){
        if (hitBox.overlaps(rect)){
            return true;
        }
        return false;
    }

    public boolean isInRange(Rectangle rect){
        if (range.contains(rect.x, rect.y)){
            return  true;
        }
        return false;
    }


}
