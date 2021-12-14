<p align="center">
<img src="logo.jpg" width="20%" height="20%">

<div align="center">

# TweetAnalyzer@UnivPM
## PROGETTO PROGRAMMAZIONE A OGGETTI 2021-2022
#### Applicazione Java che permette di ricercare tweet in base ad hashtag, filtrare per giorno e provenienza geografica e fare statistiche su giorno, provenienza geografica e hashtag presenti.

</div>

# Rotte applicazione

> **POST** /config

Permette di settare url API e Bearer token (DEFAULT: proxy univpm e nessun token)

**NOTA: deve essere lanciato prima di tutto il resto ad esclusione dei metadati**

> **GET** /tweet/metadata

Restituisce metadati

> **GET** /tweet/get/{method}

Permette di scaricare tweet inserendo fino a 3 hashtag scegliendo se devono esserci tutti con **{method} = and**
o soltanto uno con **{method} = or**

Inoltre è possibile scegliere la lingua dei tweet e quanti tweet scaricarne (DEFAULT: italiana, 5)

> **GET** /tweet/data

Permette di visualizzare i tweet salvati precedentemente

> **GET** /tweet/filter/day

Permette di visualizzare i tweet scritti in un determinato giorno (DEFAULT: giorno corrente)

> **GET** /tweet/filter/geo

Permette di visualizzare i tweet provenienti da una specifica località (se tweet non geolocalizzato si usa posizione utente)

> **GET** /tweet/stats/day

Permette di visualizzare quanti post sono stati scritti nel giorno specifico e nei 2 precedenti (DEFAULT: giorno corrente)

> **GET** /tweet/stats/geo

Permette di visualizzare quanti post provengono da una specifica località (se tweet non geolocalizzato si usa posizione utente)

> **GET** /tweet/stats/hash

Permette di visualizzare quanti post contengono lo specifico hashtag (DEFAULT: massimo, minimo e media degli hashtag per tweet)
