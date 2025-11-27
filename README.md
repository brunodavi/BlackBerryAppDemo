# BlackBerry 8520 J2ME Project (Gradle)

Este é um projeto base (*boilerplate*) para desenvolvimento de aplicativos J2ME (Java Micro Edition) focados em dispositivos BlackBerry legacy (OS 4.6 e 5.0), como o clássico **BlackBerry Curve 8520**.

O diferencial deste projeto é o uso do **Gradle** para automação. Isso permite compilar, pré-verificar (*preverify*) e empacotar o aplicativo de forma profissional sem depender de IDEs antigas ou configurações complexas no computador. O projeto é "autossustentável" (portable) uma vez configurado.

## 📋 Pré-requisitos

Para evitar violação de direitos autorais, este repositório **não inclui** os arquivos binários proprietários da Sun/Oracle. Você deve baixá-los oficialmente e adicioná-los ao projeto.

**1. Java (JDK)**
Você precisa de um JDK instalado (recomendado JDK 8 ou superior). O Gradle cuidará da compatibilidade com o Java 1.4 antigo automaticamente.

**2. Sun Java Wireless Toolkit (WTK)**
Você precisa baixar o kit de ferramentas oficial para obter as bibliotecas e o pré-verificador.
* **Link Oficial:** [Oracle Java Archive - Java ME Downloads](https://www.oracle.com/java/technologies/java-archive-downloads-javame-downloads.html#sun_java_wireless_toolkit-2.5.2_01b-oth-JPR)
* *Nota: O download exige uma conta gratuita na Oracle.*

---

## ⚙️ Instalação e Configuração (Pasta Deps)

Após baixar o WTK no link acima, você precisa criar uma pasta chamada `deps` na raiz deste projeto e copiar os arquivos vitais para dentro dela. O Gradle foi configurado para buscar as ferramentas ali.

A estrutura deve ficar **exatamente** assim:

**MeuProjeto/**
* **deps/**
    * **bin/**
        * `preverify1.1.exe` *(Copie da pasta bin do WTK instalado)*
        * *(Dica: Se houver DLLs na pasta bin original, copie-as também para evitar erros)*
    * **lib/**
        * `cldcapi11.jar` *(Copie da pasta lib do WTK)*
        * `midpapi20.jar` *(Copie da pasta lib do WTK)*

---

## 🚀 Como Compilar e Gerar o App

Não é necessário ter o Gradle instalado na sua máquina. Use o script *wrapper* que já vem no projeto.

**No Windows:**
Abra o terminal na pasta do projeto e digite:
`.\gradlew.bat dist`

**No Linux ou Mac:**
Abra o terminal e digite:
`./gradlew dist`

### Onde está o meu arquivo?
Se a compilação der certo, o arquivo `.jar` final, pronto para o celular, estará em:
📂 **`build/libs/MeuAppBlackBerry.jar`**

---

## ❗ Solução de Problemas (Troubleshooting)

### Erro: "Illegal host string starting with /" ou "Module conflict"

Se ao tentar instalar o aplicativo no BlackBerry você receber erros estranhos como "illegal host string", "invalid manifest" ou se a instalação falhar no meio, **não se assuste**. Isso geralmente é um conflito de assinatura digital.

**Por que isso acontece?**
O BlackBerry guarda um cache da assinatura da versão anterior do seu app. Se você compilava antes com o IntelliJ nativo e agora mudou para o Gradle (ou mudou drasticamente o Manifesto), o celular tenta misturar as informações antigas com as novas e se perde, gerando erros de leitura (como achar barras `/` onde não existem).

**Como resolver:**
1.  No seu BlackBerry, vá em **Opções** > **Aplicativos**.
2.  Localize a versão antiga do seu aplicativo.
3.  **Delete/Desinstale** o aplicativo completamente.
4.  Reinicie o aparelho (opcional, mas recomendado).
5.  Instale a nova versão gerada pelo Gradle.

### Dica Profissional
Sempre incremente a versão do seu app (ex: de 1.0 para 1.1) no arquivo `build.gradle` e no `MANIFEST.MF` antes de gerar um novo build. Isso ajuda o celular a entender que é uma atualização legítima.

---

## 🛠️ Estrutura do Projeto

* **`src/main/java`**: Coloque seus códigos `.java` aqui.
* **`src/main/resources/META-INF/MANIFEST.MF`**: O arquivo de identidade do app. Edite o nome e ícone aqui.
* **`deps/`**: Onde ficam as ferramentas da Oracle que você baixou.
* **`build.gradle`**: O script que faz a mágica (compila em Java 1.4, roda o preverify e limpa o manifesto).

---

## ⚖️ Aviso Legal

Este projeto não contém código proprietário da Oracle. As bibliotecas `cldcapi` e `midpapi` são necessárias apenas para compilação e devem ser obtidas pelo usuário através do site oficial da Oracle, conforme os termos de licença deles.