# PostViewer

**Aluno:** Christian Ricci
**Email:** christian.ricci@aluno.ifsp.edu.br
**Disciplina:** Desenvolvimento de Sistemas para Dispositivos Móveis

## Descrição do Aplicativo
O **PostViewer** é um aplicativo Android desenvolvido para demonstrar o consumo de APIs REST e persistência local de dados. Ele utiliza a API pública [JSONPlaceholder](https://jsonplaceholder.typicode.com/) para exibir uma lista de postagens e seus respectivos comentários.

### Funcionalidades
- **Lista de Posts:** Carrega e exibe títulos de postagens da API.
- **Detalhes do Post:** Exibe o conteúdo completo e todos os comentários associados (API + Local).
- **Comentários Locais:** Permite que o usuário adicione novos comentários que são persistidos localmente no dispositivo via Room.
- **Estado de Carregamento:** Feedback visual enquanto os dados são recuperados.

## Tecnologias e Bibliotecas
- **Linguagem:** Kotlin
- **Interface:** Jetpack Compose
- **Arquitetura:** MVVM (Model-View-ViewModel)
- **Navegação:** Jetpack Navigation
- **Persistência Local:** Room Database
- **Consumo de API:** Retrofit + Gson
- **Gerenciamento de Estado:** StateFlow + ViewModel
- **Injeção de Dependência:** (Manual / Simples via ViewModel Factory se necessário, mas neste projeto foi instanciado diretamente no ViewModel para simplificação seguindo o exemplo base).

## Decisões de Design
- **Arquitetura reativa:** O uso de `StateFlow` e `Flow` do Room permite que a UI reaja automaticamente a mudanças nos dados, especialmente ao adicionar comentários locais que aparecem instantaneamente na lista.
- **Merge de Dados:** No `PostViewModel`, os comentários da API e os comentários do banco de dados local são combinados em um único fluxo, garantindo uma experiência de usuário fluida.
- **Separação de Camadas:** O projeto segue uma estrutura clara dividida em `data` (local/remote), `model` e `ui` (composable/navigation/theme), facilitando a manutenção.

## Como Executar
1. Clone o repositório.
2. Abra o projeto no **Android Studio Hedgehog** ou superior.
3. Sincronize o projeto com os arquivos Gradle.
4. Execute o aplicativo em um emulador ou dispositivo físico com Android 8.0 (API 26) ou superior.

## Capturas de Tela
As capturas de tela do aplicativo em execução podem ser encontradas na pasta `docs/`.
