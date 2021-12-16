<p align="center">
<img src="logo.jpg" width="20%" height="20%">

<div align="center">
  
# TweetAnalyzer@UnivPM
## PROGETTO PROGRAMMAZIONE A OGGETTI 2021-2022
#### Applicazione Java che utilizza le [API di Twitter](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/search/api-reference/get-search-tweets) per fare ricerche in base ad hashtag, filtrare per giorno e provenienza geografica e fare statistiche su giorno, provenienza geografica e hashtag presenti
  
</div>

# Avvertenze

***Il progetto ha soltanto scopi didattici!*** 

Viene infatti utilizzata un API proxy fornita dal professore che permette di evitare lunghi tempi di verifica account da parte di Twitter e questo rende **impossibile utilizzare l'applicazione con chiavi proprie**. Non sarà però complicato modificare a dovere la [classe](https://github.com/ingtommi/ObjectOrientedProgramming/blob/main/TweetAnalyzer/src/main/java/it/univpm/TweetAnalyzer/service/APICall.java) che gestisce ciò.

Inoltre, va precisato come la ricerca venga effettuata soltanto sui tweet scritti durante gli *ultimi 7 giorni*. Per ulteriori informazioni visitare questo [sito](https://developer.twitter.com/en/docs/twitter-api/getting-started/about-twitter-api) e consultare i piani disponibili.

# Contenuti

* [Introduzione](#intro)
* [Configurazione](#config)
* [Rotte](#rotte)
* [Parametri](#param)
* [Formato restituto](#form)
* [Eccezioni](#eccez)
* [Test](#test)
* [Autori](#autor)

<a name="intro"></a>
## Introduzione

La funzione principale dell'applicazione è quella di **ricercare tweet in base ad hashtag** passati come parametro: se ne possono inserire da uno fino a tre e scegliere se devono essere presenti tutti quanti o ne basta uno variando il campo *{method}* della [rotta get](#2). Inoltre è possibile **distinguere per lingua** e **decidere il numero di tweet visualizzabili**.

Come richiesto dalle specifiche assegnate è anche possibile filtrare questi risultati e visualizzare delle statistiche:

* **FILTRI** 
  * *[GIORNO](#4):* vengono restituiti i tweet scritti in uno specifico giorno.
  * *[LUOGO](#5):* vengono restituiti i tweet provenienti da uno specifico luogo.

* **STATISTICHE** 
  * *[GIORNO](#6):* viene restituito il numero di tweet scritto nel giorno specifico e nei due precedenti.
  * *[LUOGO](#7):* viene restituito il numero di tweet provenienti dallo specifico luogo.
  * *[HASHTAG](#8):* viene restituito il numero di tweet contententi lo specifico hashtag.

**NOTA:** *controlla [parametri]("param") richiesti!*

<a name="config"></a>
## Configurazione

Per prima cosa è necessario clonare la repository in locale utilizzando [GitHub Desktop](https://desktop.github.com/) oppure il terminale con il seguente comando
```
git clone https://github.com/ingtommi/ObjectOrientedProgramming
```
fatto questo si può lanciare il progetto come SpringBoot application da un IDE (es. [Eclipse](https://www.eclipse.org/downloads/)) oppure da [terminale](https://www.appsdeveloperblog.com/run-spring-boot-app-from-a-command-line/) e quando il programma sarà in esecuzione sarà possibile utilizzarlo da un client (es. [Postman](https://www.postman.com/downloads/)) contattando l'indirizzo
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

<a name="param"></a>
## Parametri

N° | Parametri | Tipo | Richiesto
----- | ------------ | -------------------- | ----------------------
[2](#2) | `hashtag1, hashtag2, hashtag3, count, lang` | *String, String, String, int, String* | *SI, NO, NO, NO, NO*
[4](#4) | `day, month, year` | *int, int, int* | *NO, NO, NO*
[5](#5) | `location` | *String* | *SI*
[6](#6) | `day, month, year` | *int, int, int* | *NO, NO, NO*
[7](#7) | `location` | *String* | *NO*
[8](#8) | `hashtag` | *String* | *NO*

<a name="form"></a>
## Formato restituito

<a name=1></a>
### 1. Metadati
```json
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

<a name=2></a>
### 2. Salvataggio

Esempio con *un solo hashtag* (in questo caso **{method} = or** ma non sarebbe cambiato nulla con **and**):

![alt_text](https://github.com/ingtommi/ObjectOrientedProgramming/blob/main/photo/get.jpg?raw=true)

**NOTA:** *Gli **hashtag** vanno inseriti con il # davanti, la **lingua** secondo questi [codici](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes)!*

<a name=3></a>
### 3. Dati

Dati relativi ad esempio precedente:
```json
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
<a name=4></a>
### 4. Filtro giorno

Stesso formato dei [dati](#3) con solo tweet scritti nel giorno indicato.

<a name=5></a>
### 5. Filtro luogo

Stesso formato dei [dati](#3) con solo tweet provenienti dal luogo indicato.

**NOTA:** *In questo caso per essere visualizzato il tweet deve contenere nel luogo la parola inserita. Questo significa che se cerco **Italia** non vedrò tweet con posizione **Milano, Lombardia**!*

<a name=6></a>
### 6. Statistiche giorno

Tra default e caso specifico non cambia nulla se non i giorni indicati:
```json
{
    "Total tweets downloaded": 5.0
    "Tweets written on 2021-12-15": 5.0,
    "Tweets written on 2021-12-14": 0.0,
    "Tweets written on 2021-12-13": 0.0,
    "Percentage": 100.0
}
```

<a name=7></a>
### 7. Statistiche luogo

Default:
```json
{
    "Total tweets downloaded": 5.0
    "Tweets with no location": 2.0,
    "Tweets with unprocessable location": 0.0
    "Tweets written in Italy": 3.0,
    "Percentage of tweets with no location": 40.0,
    "Percentage of tweets with unprocessable location": 0.0,
    "Percentage of tweets written in Italy": 60.0
}
```

**NOTA:** *Si ha NO LOCATION se nè il tweet nè l'utente hanno fornito informazioni sulla posizione, mentre UNPROCESSABLE LOCATION comprende i tweet in cui la posizione non corrisponde a "Italia", "Italy" oppure nomi di comuni e regioni in **lingua italiana**. Questo significa che **Rome** o **Milan** andranno in questo gruppo!*

Specifico:
```json
{
    "Total tweets downloaded": 5.0,
    "Tweets written in Italia": 3.0,
    "Percentage": 60.0
}
```
**NOTA:** *vedi [sopra](#5)*

<a name=8></a>
### 8. Statistiche hashtag

Default:
```json
{
    "Total tweets downloaded": 5.0
    "Min hashtags per tweet": 2.0,
    "Max hashtags per tweet": 4.0,
    "Average hashtags per tweet": 3.0
}
```

**NOTA:** *Per problemi dI Twitter può accadere che il valore minimo degli hashtag sia 0 anche se impossibile visto che i tweet sono stati ricercati in base ad almeno 1 hashtag. Purtroppo questo non dipende da noi!*

Specifico:
```json
{
    "Total tweets downloaded": 5.0,
    "Tweets containing #univpm": 0.0,
    "Percentage": 0.0
}
```

<a name="eccez"></a>
## Eccezioni

Oltre alle eccezioni standard di Java sono state gestite le seguenti *eccezioni personalizzate*

* **WrongMethodException:** lanciata se *{method}* diverso da **and** oppure **or**, viene visualizzato il messaggio
```
"ERROR: wrong method!"
```
* **MissingCallExcpetion:** lanciata se utilizzo altre rotte prima di [salvare i dati](#2), viene visualizzato il messaggio
```
"ERROR: first contact http://localhost:8080/tweet/get/{method}"
```
* **IsEmptyException:** lanciata se la ricerca non ha raccolto alcun tweet, viene visualizzato il messaggio
```
"ERROR: no tweets found!"
```

<a name="test"></a>
## Test

Sfruttando il framework JUnit sono stati implementati 3 test unitari consultabili [qui](https://github.com/ingtommi/ObjectOrientedProgramming/blob/main/TweetAnalyzer/src/test/java/it/univpm/TweetAnalyzer/tests/Tests.java). Nel dettaglio:

* **Test 1:** controllo correttezza costruzione url
* **Test 2:** controllo correttezza parsing della data
* **Test 3:** controllo correttezza lancio eccezione WrongMethodException

<a name="autor"></a>
## Autori

Progetto realizzato da

* **[Tommaso Fava](https://github.com/ingtommi):** controller, service, stats, readme
* **[Marco Ciampichetti](https://github.com/MarcoCiamp):** model, filter, exception, javadoc, test

Il progetto è stato sviluppato in circa due settimane di lavoro costante nelle quali numerose commit e revisioni di coppia hanno permesso ad entrambi di avere una chiara idea su tutte le scelte adottate. Tutte le specifiche richieste sono state rispettate e sono stati introdotti anche dei **plus** come ad esempio l'*associazione tra il luogo del tweet e i comuni italiani* leggendo da [file](https://github.com/ingtommi/ObjectOrientedProgramming/blob/main/TweetAnalyzer/listaComuni.json) o la *statistica per hashtag*.

*Ringraziamo il professor [Emanuele Frontoni](https://www.linkedin.com/in/emanuelefrontoni/) e il suo staff per la disponibilità e i consigli.*
