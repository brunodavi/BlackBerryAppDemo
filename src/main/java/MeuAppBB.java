import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

// O App começa estendendo "MIDlet" (o equivalente a Activity no Android)
// "CommandListener" significa que essa classe sabe ouvir cliques de botões
public class MeuAppBB extends MIDlet implements CommandListener {

    // 1. Variáveis de Interface (O que aparece na tela)
    private Display display;       // O controlador da tela

    // 2. Componentes (As peças do Lego)
    private TextField inputNome;   // Caixa para digitar texto
    private TextField inputAno;    // Caixa para digitar números

    // 3. Comandos (Os botões do menu)
    private Command cmdCalcular;
    private Command cmdSair;

    // --- O App Começa Aqui ---
    public void startApp() {
        // Pega o controle da tela do BlackBerry
        display = Display.getDisplay(this);

        // Cria a tela vazia com título
        // Uma tela que aceita lista de coisas
        Form formularioPrincipal = new Form("Teste Basico");

        // --- Montando a Tela (Adicionando as peças) ---

        // 1. Adiciona uma mensagem fixa
        // Texto estático simples
        StringItem textoAjuda = new StringItem("Bem vindo!", "Preencha os dados abaixo:");
        formularioPrincipal.append(textoAjuda);

        // 2. Adiciona caixa de texto para Nome (Max 30 letras)
        inputNome = new TextField("Seu Nome:", "", 30, TextField.ANY);
        formularioPrincipal.append(inputNome);

        // 3. Adiciona caixa para Ano (Só aceita NÚMEROS)
        inputAno = new TextField("Ano Nasc.:", "", 4, TextField.NUMERIC);
        formularioPrincipal.append(inputAno);

        // 4. Uma barra interativa (estilo controle de volume) só para testar o Trackpad
        // Uma barra de progresso (só para ficar bonito)
        Gauge barraVisual = new Gauge("Nivel de Empolgacao:", true, 10, 5);
        formularioPrincipal.append(barraVisual);

        // --- Configurando os Botões ---
        // Command.OK = Botão principal (geralmente clique do trackpad)
        // Command.EXIT = Botão de voltar/sair
        cmdCalcular = new Command("Calcular Idade", Command.OK, 1);
        cmdSair = new Command("Sair", Command.EXIT, 0);

        formularioPrincipal.addCommand(cmdCalcular);
        formularioPrincipal.addCommand(cmdSair);

        // Diz para o formulário: "Se alguém clicar, avise a classe 'this' (eu mesmo)"
        formularioPrincipal.setCommandListener(this);

        // Finalmente, mostra tudo na tela
        display.setCurrent(formularioPrincipal);
    }

    public void pauseApp() { }

    public void destroyApp(boolean unconditional) { }

    // --- Onde a Mágica Acontece (Ouvinte de Cliques) ---
    public void commandAction(Command c, Displayable d) {

        if (c == cmdSair) {
            // Se clicou em Sair, fecha o app
            destroyApp(false);
            notifyDestroyed();
        }
        else if (c == cmdCalcular) {
            // Lógica simples
            calcularEMostrar();
        }
    }

    private void calcularEMostrar() {
        String nome = inputNome.getString();
        String anoTexto = inputAno.getString();

        if (nome.length() == 0 || anoTexto.length() == 0) {
            // Mostra um Alerta de Erro (Pop-up nativo)
            Alert erro = new Alert("Erro", "Preencha tudo!", null, AlertType.ERROR);
            erro.setTimeout(Alert.FOREVER); // Fica na tela até dar OK
            display.setCurrent(erro);
            return;
        }

        try {
            int anoNasc = Integer.parseInt(anoTexto);
            int anoAtual = 2025; // Sim, J2ME não pega data fácil, vamos fixar
            int idade = anoAtual - anoNasc;

            String mensagem = "Ola " + nome + "!\nVoce tem (ou fara) " + idade + " anos.";

            // Mostra o Resultado num Alerta de Informação
            Alert resultado = new Alert("Resultado", mensagem, null, AlertType.INFO);
            resultado.setTimeout(Alert.FOREVER);
            display.setCurrent(resultado);

        } catch (NumberFormatException e) {
            // Se digitou letras no campo de número (difícil, mas possível)
            Alert erro = new Alert("Ops", "Ano invalido", null, AlertType.ERROR);
            display.setCurrent(erro);
        }
    }
}