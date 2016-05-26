# Movie search

Application that retrieves all movies that have a title contain the search term.


## Getting Started

For a successful set up in your local machine you just have to download this
repository into your machine, then you should import this project as an 'Existing Maven Project'.

```
git init
git remote add origin https://github.com/sebachili/movie-searcher.git
git pull -u origin master
```


## Running the App

The command line application must:
* Take a single argument specifying the search term, and error out if one is not provided
* Using the Open Movie Database API as a starting point, find all movies that have a title containing the given search term, and also have a Poster available. There´s no need to actually retrieve the high resolution poster image using the sort the results by Year ascending.
* Ouput one line for each result, in the following format: 
  ```{title} [{year}] - {poster_url}```
* After the results, on a separate line, output the number of results in the format: 
  ```=> {count} result(s) found``` 

```
search term: 
the matrix

The Matrix [1999] - http://ia.media-imdb.com/images/M/MV5BMTkxNDYxOTA4M15BMl5BanBnXkFtZTgwNTk0NzQxMTE@._V1_SX300.jpg
The Matrix Revisited [2001] - http://ia.media-imdb.com/images/M/MV5BMTIzMTA4NDI4NF5BMl5BanBnXkFtZTYwNjg5Nzg4._V1_SX300.jpg
The Matrix Revolutions [2003] - http://ia.media-imdb.com/images/M/MV5BMTkyNjc4NTQzOV5BMl5BanBnXkFtZTcwNDYzMTQyMQ@@._V1_SX300.jpg
The Living Matrix [2009] - http://ia.media-imdb.com/images/M/MV5BMjAwNTU2NzExMV5BMl5BanBnXkFtZTgwNzI4Mzg2MTE@._V1_SX300.jpg

=> 4 result(s) found
```
