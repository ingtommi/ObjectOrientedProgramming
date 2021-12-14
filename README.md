<p align="center">
<img src="logo.jpg" width="20%" height="20%">

<div align="center">

# TweetAnalyzer@UnivPM
## PROGETTO PROGRAMMAZIONE A OGGETTI 2021-2022
#### Applicazione Java che permette di ricercare tweet in base ad hashtag, filtrare per giorno e provenienza geografica e fare statistiche su giorno, provenienza geografica e hashtag presenti.

</div>

# Rotte applicazione

> **GET** /tweet/metadata

Restituisce metadati

> **GET** /tweet/get/{method}

Permette di scaricare tweet inserendo fino a 3 hashtag e scegliendo se devono esserci tutti con **{method} = and**
oppure soltanto uno con **{method} = or**. 

Inoltre è possibile scegliere la lingua dei tweet e quanti tweet scaricare

**DEFAULT: lingua italiana, 5 tweet restituiti**

***NOTA: deve essere lanciato prima delle rotte visualizzate di seguito***

> **GET** /tweet/data

Permette di visualizzare i tweet salvati precedentemente

> **GET** /tweet/filter/day

Permette di visualizzare i tweet scritti nel giorno passato come parametro

**DEFAULT: giorno corrente**

> **GET** /tweet/filter/geo

Permette di visualizzare i tweet provenienti dalla località passata come parametro (se tweet non geolocalizzato si usa posizione utente)

> **GET** /tweet/stats/day

Permette di avere statistiche sui tweet scritti nel giorno specificato e nei due precedenti

**DEFAULT: giorno corrente**

> **GET** /tweet/stats/geo

Permette di avere statistiche sui tweet provenienti dalla specifica località (se tweet non geolocalizzato si usa posizione utente)

**DEFAULT: tweet da Italia leggendo da listaComuni.json**

> **GET** /tweet/stats/hash

Permette di avere statistiche sui tweet contenenti l'hashtag passato come parametro

**DEFAULT: massimo, minimo e media degli hashtag per tweet**
