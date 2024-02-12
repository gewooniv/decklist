<a name="readme-top"></a>
<br />
<div align="center">
  <a href="https://github.com/gewooniv/decklist"><h3 align="center">Decklist</h3></a>

  <p align="center">
    project_description
    <br />
    <a href="https://github.com/gewooniv/decklist"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/gewooniv/decklist/blob/main/demo/decklist_demo-resize.gif">View Demo</a>
    ·
    <a href="https://github.com/gewooniv/decklist/issues">Report Bug</a>
  </p>
</div>


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>


<!-- ABOUT THE PROJECT -->
## About The Project

This project was created in preparation for a job interview. It was created with no previous knowledge of creating Android apps or writing in Kotlin. I enjoyed creating it in about three days and look forward to working with Android studio in the future.

The assignment:

Create a single-screen native Android application showcasing a list overview of a typical card deck, including numbered cards 2-10, Joker, Queen, King, and Ace for each suit (spade, clover, heart, or diamond). You have the creative freedom to design how each card is presented within the list.

Requirements:
1. Develop an Android app using Kotlin.
2. Implement a single screen that displays a list of cards.
3. Include cards for numbered values 2-10, Joker, Queen, King, and Ace for each suit.
4. Feel free to pick in which order the cards are presented on the list .
5. Use your creativity to design the user interface to present each card distinctly within the list.




## Description

The app consists of three pages. These can be browsed by swiping the screen or selecting one of the tabs in the top side of the screen. 

The first page is an overview of all cards, with images, of a deck of playing cards. A search function has been added, where one or more cards can be searched in several ways. It is a singleline searchbar, that disables the inscreen-keyboard on enter. All the data and images are retrieved from the API and therefor not stored in the app. This opens the door to easily switch from a deck of playing cards, to other items. 

The second page is a list of cards. These can be rearranged by pressing down briefly. The idea is that when the app is used for, for example, a Trading Card Game or deckbuilding game, a fixed composition of cards can be stored here. 

The last page is an about page. Here you can find information about me and the project.

<a name="demo"></a>
<div align="center">
[![Demo]][demo-url]
</div>


### Built With

[![Android_studio]][Android_studio-url]
[![Jetpack_compose]][Jetpack_compose-url]

Retrofit is a popular library for making network requests in Android apps. This is used to retrieve the cards via an API GET request from [Deck of Cards API](https://deckofcardsapi.com). 

A draggable list was created using [Compose Reorderable](https://github.com/aclassen/ComposeReorderable). This is a Jetpack Compose modifier enabling reordering by drag and drop in a LazyList and LazyGrid.




<!-- ROADMAP -->
## Roadmap

* Remove code from MainActivity without breaking the retrieval of items from the API
* Improve list to make items clickable and show data
* Improve list page with sorting, filter and sets
* Write automated tests
* Implement a second API call: (1) retrieve a deck incl jokers -> (2) pull 54 cards

See the [open issues](https://github.com/gewooniv/decklist/issues) for a full list of proposed features (and known issues).




<!-- CONTACT -->
## Contact

Ivo Eijgenraam - gewooniv@gmail.com

Project Link: [https://github.com/gewooniv/decklist](https://github.com/gewooniv/decklist)

<p align="right">(<a href="#readme-top">back to top</a>)</p>




<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [Denis Panjuta](https://calco.udemy.com/course/android-kotlin-developer/)
* [Philipp Lackner](https://www.youtube.com/@PhilippLackner)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[demo-url]: https://github.com/gewooniv/decklist/blob/main/demo/decklist_demo-resize.gif
[contributors-shield]: https://img.shields.io/github/contributors/gewooniv/decklist.svg?style=for-the-badge
[contributors-url]: https://github.com/gewooniv/decklist/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/gewooniv/decklist.svg?style=for-the-badge
[forks-url]: https://github.com/gewooniv/decklist/network/members
[stars-shield]: https://img.shields.io/github/stars/gewooniv/decklist.svg?style=for-the-badge
[stars-url]: https://github.com/gewooniv/decklist/stargazers
[issues-shield]: https://img.shields.io/github/issues/gewooniv/decklist.svg?style=for-the-badge
[issues-url]: https://github.com/gewooniv/decklist/issues
[license-shield]: https://img.shields.io/github/license/gewooniv/decklist.svg?style=for-the-badge
[license-url]: https://github.com/gewooniv/decklist/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/ivoeijgenraam
[Android_studio]: https://img.shields.io/badge/AndroidStudio-563D7C?style=for-the-badge&logo=android&logoColor=white
[Android_studio-url]: https://developer.android.com/studio
[Jetpack_compose]: https://img.shields.io/badge/JetpackCompose-20232A?style=for-the-badge&logo=android&logoColor=61DAFB
[Jetpack_compose-url]: https://developer.android.com/jetpack/compose

