# Projeto Gestor Vestuário

Esse é o trabalho da cadeira de técnicas de programação da ufc,
que possui a seguinte descrição:O programa GVP - Gestor de Vestuário Pessoal, será um software desktop para gerir o vestuário de
uma pessoa. Muitas pessoas acabam acumulando muitas roupas e acessórios em casa, sem saber
quando compraram, quantas vezes são utilizados, lavados, quais peças combinam, etc. É comum
também ocorrer empréstimos para familiares ou amigos e os itens não voltarem mais. Muitas vezes
também não se explora as combinações entre os itens de vestuário, para resolver esta questão, será
possível montar um “look” que é um conjunto de itens de vestuário. Este programa é o ponto central
para ter o vestuário pessoal organizado virtualmente.


# Estrutura de projeto

````
org.example/
├── controller/       ← Coordena a lógica entre view, service e model
├── service/          ← Contém regras de negócio mais complexas, validações
├── dao/              ← Acesso a dados persistidos (JSON via Gson)
├── database/         ← Caminhos, inicialização de arquivos, leitura base
├── interfaces/       ← Interfaces de comportamento (IEmprestavel, ILavavel, etc.)
├── model/            ← Entidades de domínio (Item, Look, Acessório, etc.)
├── enums/            ← Tipos enumerados 
├── exceptions/       ← Exceções personalizadas (ex: PersistenciaException)
├── utils/            ← Funções utilitárias (ex: manipuladores de JSON)
├── view/             ← Telas Swing, listeners e componentes visuais
└── Main.java         ← Ponto de entrada da aplicação
````


## Destaques do projeto
- Gerenciamento completo de itens: funcionalidades para criar, atualizar, excluir, visualizar e buscar itens de vestuário.
- Aplicação de princípios de orientação a objetos: uso de herança, polimorfismo, encapsulamento e interfaces.
- Arquitetura baseada em MVC + Service: separação clara de responsabilidades entre camadas de controle, serviço, modelo, visualização e persistência.
- Definição e uso de exceções específicas para cenários da aplicação.
- Gerenciamento de lavagens: Criar lavagem, adicionar item na lavagem, registrar lavagem, remover item da lavagem
- Montar looks:  criação de combinações de itens (looks) a partir do acervo disponível.
- Estatísticas:exibição dos itens mais e menos utilizados, mais e menos lavados, emprestados, entre outros dados relevantes.
- Uso do json para simular um banco de dados e persistir os dados.


## Tecnologias utilizadas

- Java:  Linguagem principal utilizada no desenvolvimento do sistema.
- Gson: Biblioteca para serialização e desserialização de objetos Java em JSON.
- Swing: toolkit gráfico para construção da interface de usuário.
- Maven: ferramenta de gerenciamento de dependências e automação de builds.

