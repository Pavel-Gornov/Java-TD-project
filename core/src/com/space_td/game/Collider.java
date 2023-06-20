package com.space_td.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Collider {
    public enum ColliderType {
        RECTANGLE, CIRCLE, POLYGON
    }

    public ColliderType colliderType;
    public Rectangle rCollider;
    public Circle cCollider;

    public Collider(Rectangle rCollider) {
        this.rCollider = rCollider;
        this.colliderType = ColliderType.RECTANGLE;
    }

    public Collider(Circle cCollider) {
        this.cCollider = cCollider;
        this.colliderType = ColliderType.CIRCLE;
    }

}
