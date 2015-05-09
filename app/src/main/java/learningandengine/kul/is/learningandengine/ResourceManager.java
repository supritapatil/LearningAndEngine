package learningandengine.kul.is.learningandengine;

import android.graphics.Typeface;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/**
 * Created by pradipp on 02-05-2015.
 */
public class ResourceManager
{
    private static final ResourceManager INSTANCE = new ResourceManager();
    public GameActivity activity;
    public Engine engine;
    public Camera camera;
    public VertexBufferObjectManager vbom;
    public ITiledTextureRegion playerTextureRegion;
    public ITiledTextureRegion enemyTextureRegion;
    public ITextureRegion cloud1TextureRegion;
    public ITextureRegion cloud2TextureRegion;
    public ITextureRegion platformTextureRegion;
    public Sound soundFall;
    public Sound soundJump;
    public Music music;

    public Font font;
    public BuildableBitmapTextureAtlas gameTextureAtlas;

    public void loadFont() {
        font = FontFactory.createStroke(activity.getFontManager(), activity.getTextureManager(), 256, 256,
                Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD), 50, true, Color.WHITE_ABGR_PACKED_INT,
                2, Color.BLACK_ABGR_PACKED_INT);
        font.load();
    }

    public void loadGameAudio() {
        try {
            SoundFactory.setAssetBasePath("sfx/");
            soundJump = SoundFactory.createSoundFromAsset(activity.getSoundManager(),activity,"jump.mp3");
            soundFall = SoundFactory.createSoundFromAsset(activity.getSoundManager(),activity,"fall.mp3");

            MusicFactory.setAssetBasePath("mfx/");
            music = MusicFactory.createMusicFromAsset(activity.getMusicManager(),activity,"music.mp3");

        } catch (Exception e) {
            throw new RuntimeException("Error while loading audio",e);
        }


    }
    public void loadGameGraphics(){
    BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(),1024,512, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        playerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas,activity.getAssets(),"player.png",7,3);
        enemyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity.getAssets(), "enemies.png", 2, 1);
       // enemyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas,activity.getAssets(),"enemies_spritesheet.png",);
        platformTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "platform.png");
        cloud1TextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "cloud1.png");
        cloud2TextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "cloud2.png");

        try{
            gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(2,0,2));
            gameTextureAtlas.load();
        }catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e){
            throw new RuntimeException("Error while loading game extures",e);
        }

    }

    public void create(GameActivity activity,Engine engine,Camera camera, VertexBufferObjectManager vbom) {
        this.activity=activity;
        this.camera=camera;
        this.engine = engine;
        this.vbom = vbom;

    }

    private ResourceManager() {
    }
    public static ResourceManager getInstance() {
        return INSTANCE;
    }
}
