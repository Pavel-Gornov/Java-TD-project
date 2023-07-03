package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Shaders {
//    public static String glowShader_vertex="uniform mat4 u_projTrans;attribute vec4 a_position;attribute vec2 a_texCoord0;varying vec2 v_texCoord;void main() {gl_Position = u_projTrans * a_position;v_texCoord = a_texCoord0;}";
//    public static String glowShader_fragment="uniform sampler2D u_texture;uniform float u_glowSize;varying vec2 v_texCoord;void main() {vec4 color = texture2D(u_texture, v_texCoord);float dist = length(v_texCoord - vec2(0.5));float glow = smoothstep(0.5 - u_glowSize, 0.5, dist);gl_FragColor = vec4(color.rgb * glow, color.a);}";

    public static String glowShader_vertex="attribute vec4 a_position;\n" +
            "attribute vec2 a_texCoord0;\n" +
            "\n" +
            "uniform mat4 u_projTrans;\n" +
            "\n" +
            "varying vec2 v_texCoord0;\n" +
            "\n" +
            "void main() {\n" +
            "    v_texCoord0 = a_texCoord0;\n" +
            "    gl_Position = u_projTrans * a_position;\n" +
            "}";
    public static String glowShader_fragment="#ifdef GL_ES\n" +
            "precision mediump float;\n" +
            "#endif\n" +
            "\n" +
            "varying vec2 v_texCoord0;\n" +
            "\n" +
            "uniform sampler2D u_texture;\n" +
            "uniform float u_glowStrength;\n" +
            "uniform vec4 u_glowColor;\n" +
            "\n" +
            "void main() {\n" +
            "    vec4 color = texture2D(u_texture, v_texCoord0);\n" +
            "    gl_FragColor = mix(color, u_glowColor, u_glowStrength);\n" +
            "}";
    public static ShaderProgram glowShader = new ShaderProgram(glowShader_vertex, glowShader_fragment);
//    public static String glow2D="uniform float u_glowIntensity;void main() {vec4 color = texture2D(u_texture, v_texCoord);vec4 glowColor = vec4(1.0, 1.0, 0.0, 1.0);gl_FragColor = color + glowColor * u_glowIntensity;}";


}
