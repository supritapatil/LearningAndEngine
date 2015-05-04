package learningandengine.kul.is.learningandengine;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

/**
 * Created by pradipp on 02-05-2015.
 */
public abstract class AbstractScene extends Scene{
    protected ResourceManager res = ResourceManager.getInstance();
    protected GameActivity activity = res.activity;
    protected Engine engine = res.engine;
    protected Camera camera = res.camera;
    protected VertexBufferObjectManager vbom = res.vbom;

    public abstract  void  populate();
    public void destroy(){

    }
    public void onBackKeyPressed() {
        Debug.i("Back key pressed");
    }

    public abstract void onPause();
    public abstract void onResume();
}
