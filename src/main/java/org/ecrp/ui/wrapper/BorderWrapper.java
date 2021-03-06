package org.ecrp.ui.wrapper;

import org.ecrp.ui.util.CssProps;
import javafx.scene.Node;

/**
 * 指定一个边框样式，可反复用于界面元素上
 */
public class BorderWrapper {

    private String color;
    private BorderStyle borderStyle;
    private double width;

    private BorderWrapper(String color, BorderStyle borderStyle, double width) {
        this.color = color;
        this.borderStyle = borderStyle;
        this.width = width;
    }

    public static BorderWrapper of(String color, BorderStyle style, double width) {
        return new BorderWrapper(color, style, width);
    }

    public <T extends Node> T wrap(T node) {
        CssProps cssProps = new CssProps()
                .put("-fx-border-style", this.borderStyle.name())
                .put("-fx-border-color", this.color)
                .put("-fx-border-width", String.valueOf(this.width));
        cssProps.applyTo(node);
        return node;
    }

    public enum BorderStyle {
        none, solid, dotted, dashed,
    }
}
