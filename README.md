# Eulerity Android Hackathon

My entry in the [Eulerity Android Hackathon](https://eulerity-hackathon.appspot.com/). 

<img src="https://i.imgur.com/wjX362X.png" width="250" height="450"> <img src="https://i.imgur.com/JNKADgO.png" width="250" height="450">

## Presentation Pattern

This project follows the Model-View-Presenter (MVP) presentation pattern. This breaks down to:

- Model: A data management layer that handles any business logic

- View: Responsible for displaying data from the Presenter and notifying the Presenter of any user input

- Presenter: A mediator layer between the model and the view that connects the two, it contains all the presentation logic,
 like updating the model and reacting to input from the view 


Each screen has a `Contract` that outlines interfaces for the relationship between the `View` and `Presenter`, each corresponding 
`View` or `Presenter` implements their interface and uses it to communicate between each other. 


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