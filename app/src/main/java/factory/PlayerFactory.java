package factory;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

import entity.Player;
import learningandengine.kul.is.learningandengine.ResourceManager;

/**
 * Created by pradipp on 02-05-2015.
 */
public class PlayerFactory {
    private static PlayerFactory INSTANCE = new PlayerFactory();
    private VertexBufferObjectManager vbom;

    private PlayerFactory() {
    }

    public static PlayerFactory getInstance() {
        return INSTANCE;
    }

    public void create(VertexBufferObjectManager vbom) {
        this.vbom = vbom;
    }
    public Player createPlayer (float x, float y){
        Player player = new Player(x,y, ResourceManager.getInstance().playerTextureRegion,vbom);
        player.setZIndex(2);
        return player;
    }
}
