package entity;

import com.badlogic.gdx.physics.box2d.Body;

import org.andengine.entity.IEntity;

/**
 * Created by pradipp on 25-05-2015.
 */
public interface CollidableEntity extends IEntity {
    public void setBody(Body body);
    public Body getBody();
    public String getType();

}
