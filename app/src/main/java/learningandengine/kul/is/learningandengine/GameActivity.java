package learningandengine.kul.is.learningandengine;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.IGameInterface;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;

import java.io.IOException;


public class GameActivity extends BaseGameActivity {

    private static final int CAMERA_WIDTH = 480;
    private static final int CAMERA_HEIGHT  = 800;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        IResolutionPolicy resolutionPolicy = new FillResolutionPolicy();
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, resolutionPolicy, camera);
        engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        engineOptions.getRenderOptions().setDithering(true);
        Debug.i("Engine Configured");
        return engineOptions;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
       ResourceManager.getInstance().create(this,getEngine(),getEngine().getCamera(),getVertexBufferObjectManager());
        ResourceManager.getInstance().loadGameGraphics();
        ResourceManager.getInstance().loadFont();
        ResourceManager.getInstance().loadGameAudio();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
    Scene scene = new GameScene();
        scene.getBackground().setColor(Color.CYAN);
        pOnCreateSceneCallback.onCreateSceneFinished(scene);
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
        AbstractScene scene = (AbstractScene) pScene;
        scene.populate();
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
}
