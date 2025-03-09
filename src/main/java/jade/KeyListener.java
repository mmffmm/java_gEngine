package jade;

import static org.lwjgl.glfw.GLFW.*; // GLFW_PRESS, GLFW_

public class KeyListener {
    private static KeyListener instance;
    private boolean keyPressed[] = new boolean[350];// amount of key bindings they got

    private KeyListener(){

    }

    public static KeyListener get(){
        if (KeyListener.instance == null){
            KeyListener.instance = new KeyListener();
        }

        return KeyListener.instance;
    }


    // this probably linked to lwjgl by the callback, then it was send back as a response, telling what key was pressed.
    public static void keyCallBack(long window, int key, int scancode, int action, int mods){// action = keypressed, keyreleased. key = which key pressed
        if (action == GLFW_PRESS){
            get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE){
            get().keyPressed[key] = false;
        }
    }

    public static boolean isKeyPressed(int keyCode){
        //        if (keyCode < get().keyPressed.length){
        //            return get().keyPressed[keyCode];
        //        } else {
        //            return false;
        //        }
        return get().keyPressed[keyCode];

    }

}
