This is the backend implementation of a social network app, in this case named Mingo, developed for Harry Bethancourt <mingotech01@gmail.com>.

The application is written in Java 11 on the Springboot framework. It is designed to be a pure backend implementation - i.e. there are no web pages served except at "/" and a page implemented for testing and viewing the database at "/test". All data within the database is to be accessible via JSON and RESTful endpoints, and the goal was to be able to full CRUD all data from RESTful endpoints, although particularly the update and delete functionality is not entirely implemented. In fact, there are several items that aren't 100% implemented - amongst them friendship, offers, and visits - in part due to time constraints on developing the project, in part due to overreach scoping the project, and in some cases due to a failure to communicate requirements and/or understand how to establish requirements. Nonetheless, it is very doubtful that I will be revisiting/updating this project in the future.

Although this implementation is intended for use with MySQL, there's no reason it couldn't be used with PostGres, Mongo, or any other database that is implementable with Springboot.

This particular implementation **has not implemented Spring security.** This is **not** intended to be used as a production codebase; passwords are stored unhashed, there's no protection on any of the RESTful endpoints, and essentially no protection for any user's data. Caveat emptor.

The Docker containerized version of this backend is available at https://hub.docker.com/repository/docker/tedmccormick/mingo:demo0.0.1

Here's a png of the database design - it's (more or less) what is functional after building in springboot.

![Mingo Database Design](mingo db.png?raw=true "Mingo Database Design")

