package jade;

import org.lwjgl.Version;
import org.lwjgl.opengl.GL;


import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import static org.lwjgl.glfw.Callbacks.*;

public class Window {

    private int width, height;
    private String title;
    private long glfwWindow;

    private float r,g,b,a;
    private boolean fadeToBlack = false;

    private static Window window = null;

    private Window(){
        this.width = 1920;
        this.height = 1080;
        this.title = "Mario";
        r = 1;
        b = 1;
        g = 1;
        a = 1;
    }

    public static Window get(){ // singleton
        if(Window.window == null){
            Window.window = new Window();
        }

        return Window.window;
    }

    public void run(){
        System.out.println("Hello LWJGL "+ Version.getVersion()+"!");

        init();
        loop();

        // Free the memory once loop exits
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate GLFW and the free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init(){
        // Setup error callback
        // System.err.println("Error detected");
        GLFWErrorCallback.createPrint(System.err).set();

        // Init GLFW
        if(!glfwInit()){ // error thrown if fail init
            throw new IllegalStateException("Unable to Initialize GLFW.");
        }

        // Config
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create Window
        // memory address, of the window, location etc.
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if(glfwWindow == NULL){
            throw new IllegalStateException("Failed to create GLFW window.");
        }

        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallBack);

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities(); // IMPORTANT AF, IDK WHY. BINDINGS SUMTHIN

    }

    public void loop(){
        while(!glfwWindowShouldClose(glfwWindow)){
            // Poll Events
            glfwPollEvents();

            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if (fadeToBlack){
                r = Math.max(r - 0.01f, 0);
                g = Math.max(r - 0.01f, 0);
                b = Math.max(r - 0.01f, 0);
            }

            if (KeyListener.isKeyPressed(GLFW_KEY_SPACE)){
//                System.out.println("Space key is pressed");
                fadeToBlack = true;
            }


            glfwSwapBuffers(glfwWindow);
        }
    }
}
