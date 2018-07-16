# Aadvark
A Android library with common code that can be used across projects

## Warning
This a personal library, and is not intended to be used by others. The Api may change at anytime.

## Adapters
Simple data holding classes which will usually extend stock android classes

##### Page adapters
- **FragmentPageAdapter**
- **ViewPageAdapter**

## Analytics
Idea is to use only one object for implementation of an analytics system - to prevent scattering of analytics code throughout the app. Every analytics system is encapsulated in one class which extends **AnalyticsTracker**. The **AnalyticsOrchestrator** is initilised with all the trackers on startup. From then, any class that requires analytics must only inject the orchestrator and call the appropriate analytics event method - which will foreward that analytics event to all trackers.

For ease of use common analytics classes are included
- **FirebaseAnalytics** Contains analytics, crash reporting, and user properties

## Canvas
This is a collection of classes which makes drawing on the canvas simpler. Classes are either objects which can be drawn on the canvas or views that extend canvas to eliminate boilerplate code.

##### Classes
- **AnimatedCanvas**
- **CanvasRectangle**

## Databinding
- **ImageView**
- **Switch**
- **View**

## Engagement
#### Notifications
Extend **SystemNotificationHelper** and override appropriate values to create a custom notification. Each class corresponds to one type of notification.
Extend **SystemNotificationChannel** and override appropriate values to create a custom channel. Each class corresponds to one type of channel.
#### Alarms
Extend **SystemAlarmHelper** and override appropriate values to create a custom alarm. Each class corresponds to one type of alarm. Also need to extend **SystemAlarmReceiver** to handle alarms.

## Entities
Common entities

## Extensions
A collection of functions that are named in an appropriate manner that allows them to be used from simply looking at a classes autocomplete. They provide a layer on top of android to simplify the api.

## Input filters
Used by EditTexts to validify text as it's written

Includes
- **CharacterInputFilter**

## Library Helpers
- **Dagger**

## Listeners
Simple listeners that make a 3 method listener into a one method listener.

Listeners include:
- **AfterTextChangedListener**

## Managers
Classes that manage some aspect of android.

Managers include:
- **ToastManager** To display, style, and hold reference to toasts

## Providers
These classes take an android class in the constructor, and provide an abstraction for fetching data from android (e.g. string resources). These can be using in none view classes (e.g. viewModels) without using android dependencies, so the viewModel can be build in a pure kotlin module. Strictly speaking this violates clean architecture, but they provide a good trade off between limiting android dependencies to the view and speed of development.

The providers include:
- **ResourceProvider**


## Security
Anything to do with securing the app and the users data.

Includes:
- **EncryptionHelper**

## Time
**TimeWrapper** gives an interface over the java 310 library to deal with everything time related

## Views
Simple self contained custom views
- **Recycler Item decorations**
- **CircularImageView**
- **NonSwipeViewPager**
- **TouchDisabableFrameLayout**
- **DrawBehindStatusBarLayout**
