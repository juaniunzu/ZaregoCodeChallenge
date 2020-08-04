Zarego Code Challenge

In this project, I've used Cloud Firestore as the main DB. This library allows to make changes in the app even in offline mode;
when the data changes, every change is kept in cache until the device is able to connect to internet again. When connection is restored,
the Cloud Firestore updates all changes.

I've used FirestoreRecyclerAdapter (part of FirestoreUI library) as the extension for the list adapter instead of regular RecyclerView
Adapter since that class allowed me to observe the DB in real time and to listen for changes to update the UI accordingly.

Even though there are methods that connect views directly with the DB (getSnapshots() is one of them, this method is property of
FirestoreRecyclerAdapter class, is called inside the Adapter and in this case is used to delete entries with side swipe), the rest of the
interactions between views and models are done following the MVC architecture pattern.

The app allows to insert new entries with a FAB, to see the user detail by clicking the list element, to update/edit data from existing
users, and delete the user by side swiping the list element.
