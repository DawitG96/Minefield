# Minefield

## Java development of the famous Microsoft's game 'Minesweeper'

| **Fioretti Stefano** | **@f19stefano96** |
| **Gulino Dawit** | **@DawitG96** |
| Bertolazzi Giacomo | @Berack96 |

**Table of Contents**

[TOCM]

[TOC]

## English --coming soon

## Italiano

Classe Cell
Questa classe è utile per vedere lo stato di una cella. Vi sono dei parametri privati che vengono modificati tramite i metodi. Invece il parametro numMineNear è pubblico perché così può essere modificato anche da una classe esterna. I metodi in questa classe servono per vedere in che stato si trova una cella e per modificarlo.
I possibili stati sono definiti nella classe Status (Covered, Uncovered, Dangerous, Exploded).

Classe Field
La classe Field permette di generare una matrice di classi Cell. Vi sono tre parametri costanti pubblici, il cui valore cambia in base alla difficoltà del gioco, e tre parametri protected modificabili dalle classi che li ereditano. Questa classe è astratta poiché vi è il metodo buildMatrixWithMines che deve essere definito in altre classi. In questa classe ci sono dei metodi utili per gestire le funzioni basi del campo.

Classe FieldSafe
Il metodo buildMatrixWithMines viene implementato in modo tale che il campo generato non abbia una mina nella prima cella scelta e in quelle attorno.

Classe FieldNotSafe
Il metodo buildMatrixWithMines viene implementato in modo tale che anche la prima cella scelta e quelle attorno possano contenere delle mine.

Classe Main
Permette di far partire il gioco o in console o attraverso un’interfaccia grafica.

Classe Console
La classe Console permette all’utente di giocare tramite la console interagendo con essa per impostare la difficoltà e scoprire le celle fino a quando non vincerà o perderà la partita. Le difficoltà sono definite nella classe Difficulty(Easy, Medium, Hard, Custom).

Classe Graphic
La classe Graphic permette di giocare con un’interfaccia grafica. Il metodo Start imposta come visibile la finestra HomeWindow.

Classe HomeWindow – GameWindow – EndWindow
Queste 3 classi estendono JFrame e permettono di visualizzare le finestre rispettivamente del menù iniziale, della griglia del campo e del menù finale.

Classe CellObserver
Serve per mantenere aggiornato lo stato della cella, ovvero se essa è stata scoperta o meno.

Classe HomeListener
Estende la classe actionListener. Permette di capire quale bottone è stato premuto e sceglie l’azione da eseguire.

Classe GameListener
Estende la classe mouseListener. Permette di capire quale cella del campo è stata selezionata e in base al bottone del mouse cliccato esegue un’azione.

Classe EndListener
Estende la classe actionListener. In base al bottone selezionato permette di chiudere il gioco o ricominciare una partita.

Classe Home
Classe che consente di impostare i valori da usare per generare il campo.

Classe Game
Classe che restituisce il numero di bandierine utilizzabili in base alla difficoltà del gioco.

Classe GameUtility
Contiene una serie di metodi pubblici generici quali la generazione del campo, generazione delle celle e connessione agli observer e output di vittoria/sconfitta.

Classe GameIcons
Permette di creare le immagini che devono essere mostrate nella griglia di gioco.

Classe StringToShow
Questa classe raggruppa tutte le costanti in modo che se noi le dobbiamo modificare basta farlo una volta solo.

Classe ColumnException – MineTooHighException – MineTooLowException –
RowException
Classi che restituiscono delle specifiche eccezioni riguardo alle regole basi per la gestione del gioco.
