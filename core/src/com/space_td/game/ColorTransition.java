package com.space_td.game;

import com.badlogic.gdx.graphics.Color;

public class ColorTransition {

    private Color startColor;
    private Color targetColor;
    private float duration;
    private float elapsedTime;
    private boolean isTransitioning;

    public ColorTransition(Color startColor, Color targetColor, float duration) {
        this.startColor = startColor;
        this.targetColor = targetColor;
        this.duration = duration;
        this.elapsedTime = 0f;
        this.isTransitioning = false;
    }

    public Color getCurrentColor() {
        if (isTransitioning) {
            float lerpAmount = elapsedTime / duration;
            return startColor.lerp(targetColor, lerpAmount);
        } else {
            return startColor;
        }
    }

    public void startTransition() {
        isTransitioning = true;
    }

    public void update(float deltaTime) {
        if (isTransitioning) {
            elapsedTime += deltaTime;
            if (elapsedTime >= duration) {
                isTransitioning = false;
            }
        }
    }
}