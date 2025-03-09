package jade;

import org.lwjgl.opengl.GL;

import java.awt.event.KeyEvent;

import static org.lwjgl.opengl.GL20.*;

public class LevelEditorScene extends Scene{

//    private boolean changingScene = false;
//    private float timeToChangeScene = 2.0f;

    private String vertexShaderSrc = "#version 330 core\n" +
            "        layout (location=0) in vec3 aPos;\n" +
            "        layout (location=1) in vec4 aColor;\n" +
            "\n" +
            "        out vec4 fColor;\n" +
            "\n" +
            "        void main(){\n" +
            "\n" +
            "        fColor = aColor;\n" +
            "        gl_Position = vec4(aPos, 1.0);\n" +
            "}";

    private String fragmentShaderSrc = "#version 330 core\n" +
            "\n" +
            "        in vec4 fColor;\n" +
            "\n" +
            "        out vec4 color;\n" +
            "\n" +
            "        void main()\n" +
            "{\n" +
            "        color = fColor;\n" +
            "}";


    private int vertexID, fragmentID, shaderProgram;

    public LevelEditorScene(){
        System.out.println("Inside level editor scene");
    }

    @Override
    public void init(){


        // First load and compile the vertex shader
        vertexID = glCreateShader(GL_VERTEX_SHADER);

        // Pass the shader source to the GPU
        glShaderSource(vertexID, vertexShaderSrc);
        glCompileShader(vertexID);

        // Check for error in compilation process
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if (success == GL_FALSE){
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'defaultShader.glsl'\n\tVertex shader compilation failed.");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }


        // First load and compile the vertex shader
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

        // Pass the shader source to the GPU
        glShaderSource(fragmentID, fragmentShaderSrc);
        glCompileShader(fragmentID);

        // Check for error in compilation process
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (success == GL_FALSE){
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'defaultShader.glsl'\n\tFragment shader compilation failed.");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }

        // Link shaders and check for errors
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexID);
        glAttachShader(shaderProgram, fragmentID);
        glLinkProgram(shaderProgram);

        // Check for linking errors
        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if (success == GL_FALSE){
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'defaultShader.glsl'\n\tLinking of shader failed.");
            System.out.println(glGetProgramInfoLog(shaderProgram, len));
            assert false : "";

        }
    }

    @Override
    public void update(float dt) {

//        System.out.println("Im in update always");
//
//        if(!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)){
//            changingScene = true;
//        }
//
//        if(changingScene && timeToChangeScene > 0){
//            timeToChangeScene -= dt;
//            Window.get().r -= dt * 5.0f;
//            Window.get().g -= dt * 5.0f;
//            Window.get().b -= dt * 5.0f;
//        } else if (changingScene){
//            Window.changeScene(1);
//        }
    }
}
