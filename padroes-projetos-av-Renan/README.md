# Padrões de Projetos — Avaliação Semestral

Projeto de estudo baseado no enunciado da avaliação. O código foi mantido simples e sem declarações de `package`: **todas as classes e interfaces ficam diretamente dentro da pasta `src` de cada questão**.

## Estrutura

```text
padroes-projetos-av-Renan/
├── README.md
├── questao1/
│   ├── config.properties
│   ├── diagrama/
│   │   └── ProvisionamentoMultinuvem.drawio
│   └── src/                 (todos os .java diretamente aqui)
└── questao2/
    ├── diagrama/
    │   └── CasaDeLeiloesOnline.drawio
    └── src/                 (todos os .java diretamente aqui)
```

## Questão 1 — Provisionamento Multinuvem

- **Abstract Factory:** `CloudFactory` e as fábricas `AwsFactory` e `AzureFactory` criam famílias compatíveis de máquinas virtuais, armazenamento e filas.
- **Singleton:** `AppConfig` carrega `config.properties` uma única vez; `AuditLog` mantém um histórico único.
- **Concorrência:** IDs usam `AtomicInteger` e o histórico usa métodos sincronizados. A `Main` dispara quatro solicitações em threads distintas e aguarda todas antes de imprimir os quatro registros.
- A `ProvisioningService` recebe uma única `CloudFactory`, calcula o custo antes de criar recursos e recusa solicitações acima do orçamento.

Para executar a partir da pasta raiz do projeto, com Java instalado:

```text
javac questao1/src/*.java
java -cp questao1/src Main
```

O arquivo `questao1/config.properties` contém a região e o orçamento mensal. O caminho é relativo à pasta raiz do projeto.

## Questão 2 — Casa de Leilões Online

- **Observer:** `Lot` notifica objetos que implementam `BidObserver`; o autor do lance não recebe sua própria notificação.
- **Strategy:** `CommissionStrategy` permite trocar a regra de comissão do lote antes do encerramento. A comissão é registrada no momento do fechamento.
- **Decorator:** `InvoiceComponent`, `Sale` e os decoradores de serviço permitem combinar serviços em qualquer ordem e repetir um serviço.
- `InvoicePrinter` imprime a fatura sem verificar quais serviços estão presentes.

Para executar a partir da pasta raiz do projeto:

```text
javac questao2/src/*.java
java -cp questao2/src Main
```

Os diagramas podem ser abertos e editados em https://app.diagrams.net/.
