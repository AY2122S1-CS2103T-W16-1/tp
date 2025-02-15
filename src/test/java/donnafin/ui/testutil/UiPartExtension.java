package donnafin.ui.testutil;

import java.util.concurrent.TimeoutException;

import org.testfx.api.FxToolkit;

import donnafin.ui.UiPart;
import javafx.scene.Parent;
import javafx.scene.Scene;

//@@author bharathcs-reused
//Reused from https://github.com/se-edu/addressbook-level4/ with minor modifications.

/**
 * Provides an isolated stage to test an individual {@code UiPart}.
 */
public class UiPartExtension extends StageExtension {
    private static final String[] CSS_FILES = {"view/DarkTheme.css", "view/Extensions.css"};

    public void setUiPart(final UiPart<? extends Parent> uiPart) {
        try {
            FxToolkit.setupScene(() -> {
                Scene scene = new Scene(uiPart.getRoot());
                scene.getStylesheets().setAll(CSS_FILES);
                return scene;
            });
            FxToolkit.showStage();
        } catch (TimeoutException te) {
            throw new AssertionError("Timeout should not happen.", te);
        }
    }
}

//@@author
