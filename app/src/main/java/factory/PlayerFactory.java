package factory;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import entity.Player;
import learningandengine.kul.is.learningandengine.ResourceManager;

/**
 * Created by pradipp on 02-05-2015.
 */
public class PlayerFactory {
    private static PlayerFactory INSTANCE = new PlayerFactory();
    private PhysicsWorld physicsWorld;
    public static final FixtureDef PLAYER_FIXTURE = PhysicsFactory.createFixtureDef(1f,0f,1f,false);
    private VertexBufferObjectManager vbom;

    private PlayerFactory() {
    }

    public static PlayerFactory getInstance() {
        return INSTANCE;
    }

    public void create(VertexBufferObjectManager vbom, PhysicsWorld physicsWorld) {
        this.physicsWorld = physicsWorld;
        this.vbom = vbom;
    }
    public Player createPlayer (float x, float y){
        Player player = new Player(x,y, ResourceManager.getInstance().playerTextureRegion,vbom);
        player.setZIndex(2);
        Body playerBody = PhysicsFactory.createBoxBody(physicsWorld, player, BodyDef.BodyType.DynamicBody, PLAYER_FIXTURE);
        playerBody.setLinearDamping(1f);
        playerBody.setFixedRotation(true);
        playerBody.setUserData(player);
        physicsWorld.registerPhysicsConnector(new PhysicsConnector(player,playerBody));
        player.setBody(playerBody);
        return player;
    }

}
