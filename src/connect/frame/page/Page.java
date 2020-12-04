package connect.frame.page;

import connect.component.GameGroup;

import java.io.FileNotFoundException;

public abstract class Page extends GameGroup {

    public Page()  {
        try {
            draw();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public abstract void draw() throws FileNotFoundException;
}
