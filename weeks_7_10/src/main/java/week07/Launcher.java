package week07;

import javafx.application.Application;

public class Launcher {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Error: No class name specified. Provide the target class name to launch.");
            System.exit(1);
        }
        
        String targetClassName = args[0];
        try {
            Class<?> clazz = Class.forName(targetClassName);
            if (Application.class.isAssignableFrom(clazz)) {
                // Forward the remaining arguments to the target JavaFX app
                String[] appArgs = new String[args.length - 1];
                System.arraycopy(args, 1, appArgs, 0, appArgs.length);
                
                Application.launch((Class<? extends Application>) clazz, appArgs);
            } else {
                System.err.println("Error: Class " + targetClassName + " is not a subclass of javafx.application.Application");
                System.exit(1);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Could not find class " + targetClassName);
            System.exit(1);
        }
    }
}
