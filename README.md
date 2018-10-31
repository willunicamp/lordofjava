# Lord Of Java - Sistema de Batalhas para RPG

O jogo Batalhas de RPG será capaz colocar personagens para se enfrentar em uma batalha. Nessa batalha, os personagens podem ter diferentes classes, que definem suas habilidades e como evoluem, níveis e itens. 
Personagens:
Terão nome, classe a que pertencem e nível. Terão também 3 atributos calculados definindo sua força física (capacidade de bater e resistência do personagem), agilidade (que define quão rápido, tanto para bater quanto esquivar-se) e a inteligência (que define sua capacidade de usar e resistir a magias), que vão depender da classe do personagem.
Eles terão também pontos de vida (PV), pontos de magia (PM) e pontos de experiência (PE).
Classes:
Nossos personagens pertencem a classes  distintas. Inicialmente, teremos as classes: arqueiro, guerreiro, mago e monstro. Cada classe define as habilidades, atributos iniciais, habilidades e evolução dos personagens a cada nível, mostrados em detalhes nas tabelas a seguir. Futuramente, novas classes de personagens podem ser adicionadas. Os atributos de cada classe de personagem também podem ser modificados para balanceamento das batalhas.

|Arqueiro|Guerreiro|Mago|Monstro|
|---|---|---|---|
|Suas flechas causam menos dano que a espada de um guerreiro, mas tem a capacidade de atacar de forma ágil! Desvia facilmente de ataques.|Ótimo em ataques corpo a corpo armado. Tem média força bruta para ataque desarmado. Sua resistência é ótima!|Magos podem lançar magias e com o uso de itens, aumentam suas habilidades.|Grande força física. Burro como uma porta. Incapaze de usar magia.|
|A cada nível, ganha 3 pontos de agilidade, 2 de inteligência e 1 de força|A cada nível, ganha 4 pontos de força, 1 de agilidade e 1 de inteligência.|A cada nível, ganha 3 pontos de inteligência, 1 de força e 2 de agilidade.|A cada nível, ganha 4 pontos de força e 1 ponto de agilidade.|




## Arqueiro

|Habilidade|Dano|Tempo de descanso (unidades)|Alvo|Pontos de Magia|
| --- | --- | --- | --- | --- |
|Flecha Encantada|nível&ast;teto(força&ast;0.3+agilidade&ast;0.5 +inteligência&ast;0.4)|7|Único|nível&ast;teto(inteligência+agilidade&ast;0.2)|
|Atirar flecha|nível&ast;teto(força&ast;0.3+agilidade&ast;0.5)|4|Único|0|
|Socar|nível&ast;teto(agilidade&ast;0.1+força&ast;0.3)|3|Único|0|

## Guerreiro
|Habilidade|Dano|Tempo de descanso (unidades)|Alvo|Pontos de Magia|
| --- | --- | --- | --- | --- |
|Espada Flamejante|nível&ast;teto(força&ast;0.3+agilidade&ast;0.5 inteligência&ast;0.4)|7|Único|nível&ast;teto(inteligência+força&ast;0.2)|
|Golpe de espada|nível&ast;teto(agilidade&ast;0.3+força&ast;0.7)|5|Único|0|
|Socar|nível&ast;teto(agilidade&ast;0.1+força&ast;0.3)|4|Único|0|

## Mago
|Habilidade|Dano|Tempo de descanso (unidades)|Alvo|Pontos de Magia|
| --- | --- | --- | --- | --- |
|Enfraquecer|nível&ast; teto(agilidade&ast;0.2+força&ast;0.3+ inteligência&ast;0.5)|5|Único|nível&ast;teto(inteligência&ast;0.5)|
|Cura Amigo|nível&ast;teto(agilidade&ast;0.2+força&ast;0.5+ inteligência&ast;0.8)|4|Único(Aliado)|nível&ast;teto(inteligência&ast;0.7)|
|Socar|nível&ast;teto(agilidade&ast;0.1+força&ast;0.1)|2|Único|0|

### Monstro
|Habilidade|Dano|Tempo de descanso (unidades)|Alvo|Pontos de Magia|
| --- | --- | --- | --- | --- |
|Socar|nível&ast;teto(agilidade&ast;0.4+força&ast;0.8)|5|Único|0|
|Chutar|nível&ast;teto(agilidade&ast;0.5+força&ast;1.0)|8|Único|0|
|Grito atordoante|nível&ast;teto(agilidade&ast;0.2+força&ast;0.4)|6|Todos|0|

## Pontos de vida, magia e experiência

Os pontos de vida (PV) de um personagem definem sua resistência a golpes. Os pontos de magia permitem ao personagem usar magias utilizando estes pontos. Abaixo, a definição de PV e PM máximos:

*Vmax = nível &ast; força + (nível &ast; agilidade/2)*
*PMmax = nível &ast; inteligência + (nível &ast; agilidade/3)*

Os pontos de experiência (PE) servem para fazer um personagem passar para o próximo nível. A cada nível os pontos de experiência voltam a zero e são necessários mais pontos para subir de nível.
PE aumenta quando um personagem é derrotado, pela seguinte equação:

*PE += nível do adversário derrotado&ast;5*

Para subir de nível, é necessário ter uma quantia de PE específica, definida pela fórmula:

*PE necessário para subir de nível = nível atual &ast; 25*

## Batalhas

Deve ser possível em nosso jogo travar uma batalha entre 3 personagens contra N inimigos. Esses 3 personagens devem ser escolhidos pelo usuário, entre guerreiro, arqueiro e mago. O usuário deve digitar o nome e escolher a classe.
Os inimigos podem ter qualquer classe, e serão inicialmente controlados pelo usuário.
Ao iniciar a batalha, o jogo deve exibir as informações dos dois times, com ID, nome, Classe, PV, PM, Nível do personagem e tempo de espera.
No momento zero, ao início do jogo, todos os personagens terão tempo de espera igual a zero. Dessa forma, todos tem uma chance de atacar nesse momento. Assim, deve-se sortear qual entre todos os personagens ataca primeiro.
Ao escolher qual personagem ataca, deve-se exibir a lista de suas habilidades, com nome, PM necessário, dano que causa, tempo de espera que ela causa e quem/quantos ela afeta.
Como cada habilidade tem um tempo de espera, quando um personagem ataca, seu tempo de espera será modificado para o tempo da habilidade usada. Ele deve ficar sem atacar até que seu tempo de descanso atinja zero (0) novamente. A cada turno, o tempo de descanso é reduzido em 1. Se ninguém puder atacar, o tempo de todos é reduzido em um e o turno se passa sem que ninguém ataque, até que alguém atinja tempo zero (0). Turnos silenciosos não precisam ser notificados na tela.
Se por acaso dois ou mais personagens puderem atacar no mesmo instante, um sorteio é feito para decidir qual deles irá atacar primeiro.
Uma vez atordoado (PV = 0), um personagem não pode atacar.
Pontos de experiência vão para todos os membros do time vencedor, incluindo aqueles que terminarem atordoados.
Através de um arquivo chamado **game.txt** serão definidas as equipes inimigas, que funcionarão como fases. O formato do arquivo será:

```
<NomeDoInimigo1> <NívelDoInimigo1>
<NomeDoInimigo2> <NívelDoInimigo2>
…
<NomeDoInimigoN> <NívelDoInimigoN>
fase
<NomeDoInimigo1> <NívelDoInimigo1>
<NomeDoInimigo2> <NívelDoInimigo2>
…
<NomeDoInimigoN> <NívelDoInimigoN>
fase
```

A cada vez que a palavra “fase” é encontrada no arquivo game.txt, a lista de inimigos é fechada para uma batalha ser travada. Dessa forma, é possível criar N batalhas em um jogo, com inimigos de diversas classes e níveis em cada batalha. Deve-se ter o cuidado de não colocar quantidade e nível dos inimigos que não condizem com o nível dos aliados. Testes devem ser feitos para conferir o balanceamento de cada batalha.

## O Projeto

O diagrama simplificado a seguir demonstra como deve ser a composição, agregação e herança das classes.

![Diagrama Simplificado](/diagrama.png)

A única classe que pede entrada de dados e exibe mensagens na tela é a **Batalha**, que controlará o jogo. A classe Main apenas deve chamar um objeto de Batalha no método main.
Reforçando: Nenhuma classe pede entrada ou exibe saída em seus métodos, com exceção da classe Batalha. Dessa forma as classes que tratam os personagens (classes de dados) ficarão genéricas e podem ser facilmente adaptadas para um projeto com uma interface visual diferente.

## Implementações Recentes
* Interface gráfica
* Adicionar texto para criar uma história no arquivo game.txt

## Implementações Futuras
* ~~Interface gráfica~~ 
* ~~Adicionar texto para criar uma história no arquivo game.txt~~
* Itens para uso nas batalhas (classes já iniciadas no pacote src/itens)
* Opção para ramificação da história, permitindo diferentes finais
* Save game
