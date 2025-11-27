import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class MeuAppBB extends MIDlet implements CommandListener {
    // Componentes da tela
    private Display display;
    private Form form;
    private Command cmdSair;
    private Command cmdOla;

    public void startApp() {
        // Pega o controle da tela do BlackBerry
        display = Display.getDisplay(this);

        // Cria um formulário (uma tela com lista de coisas)
        form = new Form("App Teste BB 2");

        // Cria botões (Comandos)
        cmdSair = new Command("Sair", Command.EXIT, 1);
        cmdOla = new Command("Diga Ola", Command.OK, 1);

        // Adiciona texto e comandos na tela
        form.append("Bem vindo ao mundo J2ME!");
        form.addCommand(cmdSair);
        form.addCommand(cmdOla);
        form.setCommandListener(this);

        // Mostra a tela
        display.setCurrent(form);
    }

    public void pauseApp() {
        // O celular tocou? O app pausa aqui.
    }

    public void destroyApp(boolean unconditional) {
        // Limpeza ao fechar
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdSair) {
            destroyApp(false);
            notifyDestroyed(); // Fecha o app
        } else if (c == cmdOla) {
            // Adiciona um texto na tela ao clicar
            form.append("\nOla Usuario do 8520!");
        }
    }
}