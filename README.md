# Eulerity Android Hackathon

My entry in the [Eulerity Android Hackathon](https://eulerity-hackathon.appspot.com/). 

Master branch will contain the initial submission and the [kotlin branch](https://github.com/WhosNickDoglio/EulerityHackathon/tree/kotlin) 
will contain a kotlin rewrite with improvements made that were not achievable in the time frame.

<img src="https://i.imgur.com/wjX362X.png" width="250" height="450"> <img src="https://i.imgur.com/JNKADgO.png" width="250" height="450">

## Package Structure


<img align="left"  src="https://i.imgur.com/3A9V3JK.png" width="250" height="350">

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

111
1
1
1
1
1
## Presentation Pattern
<img src="https://i0.wp.com/www.tinmegali.com/wp-content/uploads/2016/02/MVP.png?resize=800%2C220&ssl=1">

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