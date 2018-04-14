# Eulerity Android Hackathon

My entry in the [Eulerity Android Hackathon](https://eulerity-hackathon.appspot.com/). 

Master branch will contain the initial submission and the [kotlin branch](https://github.com/WhosNickDoglio/EulerityHackathon/tree/kotlin) 
will contain a kotlin rewrite with improvements made that were not achievable in the time frame.

## Package Structure

* data: 
    * model: contains POJOs for data objects
    * remote: contains Retrofit service
    * repo
* di: contains all dependency injection files 
* ui: contains each screen packaged by feature
    * base
    * edit
    * list
* util: contains any files that don't explicitly fall into any other package

## Presentation Pattern

This project follows the Model-View-Presenter (MVP) presentation pattern. Each screen has a `Contract` that outlines the 
relationship between the `View` and `Presenter` 
 
 
## Libraries 

- [Glide](https://bumptech.github.io/glide/) for image loading.
- [Glide-transformations](https://github.com/wasabeef/glide-transformations) for image filters.
- [Butterknife](https://jakewharton.github.io/butterknife/) for resource binding.
- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) for Retrofit calls and 
background threading.
- [Retrofit](https://square.github.io/retrofit/) for network calls.
- [Dagger](https://google.github.io/dagger/) for dependency injection.
- [EasyPermission](https://github.com/googlesamples/easypermissions) for permission requests.
- [Timber](https://github.com/JakeWharton/timber) for logging.