package learningandengine.kul.is.learningandengine;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.collision.CollisionHandler;
import org.andengine.engine.handler.collision.ICollisionCallback;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.EntityBackground;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;

import entity.Player;
import factory.PlayerFactory;

/**
 * Created by pradipp on 02-05-2015.
 */
public class GameScene extends AbstractScene implements IAccelerationListener{
    private Player player;
    private Text scoreText;
    private AnimatedSprite fly;
    float lastX = 0;

    @Override
    public void populate() {
        createBackground();
        creatPlayer();
        createHUD();

        fly = new AnimatedSprite(240,200,res.enemyTextureRegion,vbom);
        fly.animate(new long[]{100,200}, new int[]{1,0});
        //fly.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2,0,360)));
        attachChild(fly);
        registerTouchArea(player);
        engine.enableAccelerationSensor(activity,this);
        ICollisionCallback myICollisionCallback = new ICollisionCallback() {
            @Override
            public boolean onCollision(IShape pCheckShape, IShape pTargetShape) {
                fly.setColor(Color.RED);
              //  ResourceManager.getInstance().soundFall.setLoopCount(1);
               // ResourceManager.getInstance().soundFall.play();

                return false;
            }

        };

        CollisionHandler myCollisionHandler = new CollisionHandler(myICollisionCallback,fly,player);
        registerUpdateHandler(myCollisionHandler);
       /* setOnSceneTouchListener(new IOnSceneTouchListener() {
            @Override
            public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
                if(pSceneTouchEvent.isActionDown()){
                    player.clearEntityModifiers();
                    player.registerEntityModifier(new MoveModifier(1,player.getX(),player.getY(),pSceneTouchEvent.getX(),pSceneTouchEvent.getY()));
                    return true;
                }
                return false;
            }
        });*/
    }


    private void createHUD() {
        HUD hud = new HUD();
        scoreText = new Text(16,784, res.font,"0123456789", new TextOptions(HorizontalAlign.LEFT),vbom);
        scoreText.setAnchorCenter(0,1);
        hud.attachChild(scoreText);
        camera.setHUD(hud);
    }

    private void creatPlayer() {
        player = PlayerFactory.getInstance().createPlayer(240,400);
        attachChild(player);
    }

    public GameScene() {

        PlayerFactory.getInstance().create(vbom);
    }

    private void createBackground() {
        Entity background = new Entity();
        Sprite cloud1 = new Sprite(200, 300, res.cloud1TextureRegion,vbom);
        Sprite cloud2 = new Sprite(300, 600, res.cloud2TextureRegion,vbom);
        background.attachChild(cloud1);
        background.attachChild(cloud2);
        setBackground(new EntityBackground(0.82f,0.96f,0.97f,background));
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {

    }

    @Override
    public void onAccelerationChanged(AccelerationData pAccelerationData) {
        if(Math.abs(pAccelerationData.getX()-lastX) >0.5) {
            if(pAccelerationData.getX()>0) {
                Debug.i("accerlation x gt 0 is" + pAccelerationData.getX());
                player.turnRight();
            }else {
                Debug.i("accerlation x lt 0 is"+pAccelerationData.getX());
                player.turnLeft();
            }
            lastX = pAccelerationData.getX();
            Debug.i("lastx is "+lastX);
        }
        player.setX(player.getX()+pAccelerationData.getX());
    }
}
