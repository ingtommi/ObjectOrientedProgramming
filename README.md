<p align="center">
<img src="logo.jpg" width="20%" height="20%">

<div align="center">
  
# TweetAnalyzer@UnivPM
## PROGETTO PROGRAMMAZIONE A OGGETTI 2021-2022
#### Applicazione Java che utilizza le [API di Twitter](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/search/api-reference/get-search-tweets) per fare ricerche in base ad hashtag, filtrare per giorno e provenienza geografica e fare statistiche su giorno, provenienza geografica e hashtag presenti
  
</div>

# Contenuti

* [Introduzione](#intro)
* [Configurazione](#config)
* [Rotte](#rotte)
* [Formato dati](#formato)
* [Eccezioni](#eccez)
* [Test](#test)
* [Autori](#autor)

<a name="intro"></a>
## Introduzione

La funzione principale dell'applicazione è quella di **ricercare tweet in base ad hashtag** passati come parametro: se ne possono inserire da uno fino a tre e scegliere se devono essere presenti tutti quanti o ne basta uno. Inoltre è possibile **distinguere per lingua** e **decidere il numero di tweet visualizzabili**.

Come richiesto dalle specifiche assegnate è anche possibile filtrare questi risultati e visualizzare delle statistiche:

* **FILTRI** 
  * *GIORNO:* vengono restituiti i tweet scritti in uno specifico giorno.
  * *LUOGO:* vengono restituiti i tweet provenienti da uno specifico luogo.

* **STATISTICHE** 
  * *GIORNO:* viene restituito il numero di tweet scritto nel giorno specifico e nei due precedenti.
  * *LUOGO:* viene restituito il numero di tweet provenienti dallo specifico luogo.
  * *HASHTAG:* viene restituito il numero di tweet contententi lo specifico hashtag.

**NOTA:** non tutti i parametri sono necessari perchè presenti valori di **default**!

* *LINGUA:* it
* *NUMERO:* 5
* *GIORNO:* corrente
* *LUOGO:* italia (valido solo per statistica, parametro obbligatorio per filtro)
* *HASHTAG:* nessuno (vengono restituiti massimo, minimo e media di hashtag per tweet)

<a name="config"></a>
## Configurazione

Per prima cosa è necessario clonare la repository in locale utilizzando [GitHub Desktop](https://desktop.github.com/) oppure il terminale con il seguente comando:
```
git clone https://github.com/ingtommi/ObjectOrientedProgramming
```
fatto questo si può lanciare il progetto come SpringBoot application da un IDE (es. [Eclipse](https://www.eclipse.org/downloads/)) e quando il programma sarà in esecuzione sarà possibile utilizzarlo da un client (es. [Postman](https://www.postman.com/downloads/)) contattando l'indirizzo:
```
http://localhost:8080
```

<a name="rotte"></a>
## Rotte

N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
[1](#1) | ` GET ` | `/tweet/metadata` | *restituisce un JSONObject contenente le informazioni relative ai tipi di dato visualizzabili*
[2](#2) | ` GET ` | `/tweet/get/{method}` | *consente di fare la ricerca e salvare i dati e restituisce un messaggio di avvenuto salvataggio*
[3](#3) | ` GET ` | `/tweet/data` | *restituisce un JSONObject contenente i dati relativi ai tweet precedentemente salvati*
[4](#4) | ` GET ` | `/tweet/filter/day` | *restituisce un JSONObject contenente i tweet postati nel giorno inserito*
[5](#5) | ` GET ` | `/tweet/filter/geo` | *restituisce un JSONObject contenente i tweet postati dal luogo inserito*
[6](#6) | ` GET ` | `/tweet/stats/day` | *restituisce una HashMap con il numero di tweet postati nel giorno inserito e nei due precedenti*
[7](#7) | ` GET ` | `/tweet/stats/geo` | *restituisce una HashMap con il numero di tweet postati dal luogo inserito o dall'Italia*
[8](#8) | ` GET ` | `/tweet/stats/hash` | *restituisce una HashMap con il numero di tweet contenenti l'hashtag inserito*

<a name="formato"></a>
## Formato dati

<a name="1"></a>
### 1. Metadati

```
{
    "list": {
        "tweet": {
            "hashtags": "ArrayList<String>",
            "created_at": "String",
            "location": "String",
            "id": "long"
        },
        "user": {
            "name": "String",
            "created_at": "String",
            "location": "String",
            "id": "long"
        }
    }
}
```

<a name="2"></a>
### 2. Salvataggio

Esempio con un solo hashtag:

![alt_text](https://github.com/ingtommi/ObjectOrientedProgramming/blob/main/photo/get.jpg?raw=true)

<a name="3"></a>
### 3. Dati

Dati relativi ad esempio precedente:

```
{
    "list": [
        {
            "tweet": {
                "hashtags": [
                    "Milan",
                    "Conti",
                    "Calciomercato"
                ],
                "created_at": "2021-12-15",
                "location": null,
                "id": 1471074145844744195
            },
            "user": {
                "name": "MilanWorldCommunity",
                "created_at": "2010-09-11",
                "location": "Italia",
                "id": 189583499
            }
        }
    ]
}
```
