# Unsplash-API-app
Unsplash API app

This is a simple application that consumes the Unsplash https://unsplash.com/ API to show a list of images. The application demonstrates the use of Android Jetpack library built with 100% Kotlin. The main star is the use of Jetpack Compose , a new way of building native UI declaratively. More on Jetpack compose : https://developer.android.com/jetpack/compose?gclsrc=aw.ds&gclid=CjwKCAjwy_aUBhACEiwA2IHHQD7BEkC2nB7ccY95X8nnkXO8DuRK9oHDv1UfZCqNUe3c3MmGHXOPVBoCJS0QAvD_BwE

Other libraries used include:
- KotlinX serialization
- Retrofit for parsing JSON content : https://square.github.io/retrofit/
- Kotlin coroutines : Flows
- Paging3 library for loading network data in pages (pagination) more: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
- Coil image loading library: https://coil-kt.github.io/coil/
- Room database library for caching of network results locally: https://developer.android.com/training/data-storage/room
- Jetpack Datastore for storing simple primitive data : https://developer.android.com/topic/libraries/architecture/datastore?gclsrc=aw.ds&gclid=CjwKCAjwy_aUBhACEiwA2IHHQAtj1kBWNSgCNgZMrIUc7N58N3AIm6r63N3C7PqZw6jwsVvY1t7EzxoCQ44QAvD_BwE


Getting Started:
The unsplash api requires you to register an applciation before actually using the APIs in your application. The registration returns an API key and a secret key which will be used in accessing the data from the Unsplash servers. In the case of forking and cloning this repository so that you can use it , the API key is not provided for obvious security reasons and you will not be able to get any images. The solution is for you to go and register in Unsplash developers https://unsplash.com/developers , log in , register your application and an API and secret key will be generated for you.

Take the API key and use it within the project to load images.
