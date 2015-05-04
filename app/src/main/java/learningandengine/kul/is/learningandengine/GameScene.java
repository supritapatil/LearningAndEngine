package learningandengine.kul.is.learningandengine;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.background.EntityBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.adt.align.HorizontalAlign;

import entity.Player;
import factory.PlayerFactory;

/**
 * Created by pradipp on 02-05-2015.
 */
public class GameScene extends AbstractScene {
    private Player player;
    private Text scoreText;
    private AnimatedSprite fly;

    @Override
    public void populate() {
        createBackground();
        creatPlayer();
        createHUD();

        fly = new AnimatedSprite(240,200,res.enemyTextureRegion,vbom);
        fly.animate(125);
        attachChild(fly);
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
}
