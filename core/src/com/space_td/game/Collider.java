package com.space_td.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Collider {
    public enum ColliderType{
        RECTANGLE, CIRCLE, POLYGON
    }
    public ColliderType colliderType;
    public Rectangle rCollider;
    public Circle cCollider;
    public Collider(Rectangle rCollider){
        this.rCollider=rCollider;
        this.colliderType=ColliderType.RECTANGLE;
    }
    public Collider(Circle cCollider){
        this.cCollider=cCollider;
        this.colliderType=ColliderType.CIRCLE;
    }

//    public boolean overlaps(Collider otherCollider){
//        if (this.colliderType==ColliderType.CIRCLE){
//            if (otherCollider.colliderType==ColliderType.CIRCLE)
//                return cCollider.overlaps(otherCollider.cCollider);
//            if (otherCollider.colliderType==ColliderType.RECTANGLE)
//
//                return cCollider.overlaps(otherCollider.rCollider);
//
//        }
//        if (this.colliderType==ColliderType.RECTANGLE){
//
//        }
//
//
//    }
}
