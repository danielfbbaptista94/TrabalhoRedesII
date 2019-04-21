package main;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class InternetConnection 
{
    // SO name = WINDOWS ou LINUX
    String osName = System.getProperty("os.name");

    String senha = "12345";
    String s;

    public InternetConnection() {}
    
    public void checkCennection()
    {
        if (isReachableByPing("www.google.com")) 
        {
            System.out.println("Computador tem conexão... Esperando a senha");
            Scanner in = new Scanner(System.in);
            System.out.println("Senha: ");
            s = in.nextLine();
            if (validatePassword()) 
            {
                System.out.println("Connecting to the WizardNumber... ");
                try {
                    java.awt.Desktop.getDesktop().browse(new URI("file:///C:/Users/danie/OneDrive/Desktop/NumberWizard/index.html"));
                } catch (URISyntaxException | IOException ex) {
                    System.out.println("Pagina não localizada!");
                }
            }
            else
                System.out.println("Senha errada!");
        }
        else
            System.out.println("Problema com a conexão!");
    }

    // Verifica se tem conexão usando o CMD ping do WINDOWS ou LINUX
    public boolean isReachableByPing(String host) 
    {
        try {
            String cmd;
            if (osName.toLowerCase().startsWith("windows"))
                cmd = "ping -n 1 " + host; // Windows CMD
            else
                cmd = "ping -c 1 " + host; // Linux CMD

            // Start a new Process
            Process myProcess = Runtime.getRuntime().exec(cmd);
            myProcess.waitFor();

            // Return true if exitValue==0
            return myProcess.exitValue() == 0;
        } catch (IOException | InterruptedException ex) {
            return false;
        }
    }
    
    public boolean validatePassword()
    {
        return this.senha.equals(s);
    }
}