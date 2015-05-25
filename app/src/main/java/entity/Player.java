package entity;

import com.badlogic.gdx.physics.box2d.Body;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by pradipp on 02-05-2015.
 */
public class Player extends TiledSprite implements CollidableEntity {
    boolean dead = false;

    public Player(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {

        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
        setCurrentTileIndex(8);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void turnLeft() {
        setFlippedHorizontal(true);
    }

    public void turnRight() {
        setFlippedHorizontal(false);
    }

    public void fly() {
        setCurrentTileIndex(0);
    }

    public void fall() {
        setCurrentTileIndex(1);
    }

    public void die() {
        setDead(true);
        setCurrentTileIndex(2);
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        if (pSceneTouchEvent.isActionDown()) {
            clearEntityModifiers();
            return true;
        } else if (pSceneTouchEvent.isActionMove()) {
            setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
            return true;
        }
        return false;
    }

    private Body body;
    public final String TYPE = "Player";

    @Override
    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed){
   super.onManagedUpdate(pSecondsElapsed);
        if(getCurrentTileIndex() < 2) {
            if(body.getLinearVelocity().y <0) {
                fall();
            } else {
                fly();
            }
        }
    }
}
