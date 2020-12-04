# ChessP2P

Lauren: alright so the basic structure i thought we could do is have one client and one server class for each player. the server only listens for info while the client can only send it. we send a 4 character string to represent the moves, the first character is the row of the piece you are trying to move, the second character its column. the other two characters is the row column you are moving the piece to. this way we can just use the same method as the FTP server project. 

as far as getting the client/server to communicate with the prexisting chess game, i thought Chesspanel could instantiate the client and server, sending the instance of ChessModel to the server in the constructor. ChessPanel would communicate the local user's moves to the client class with a method.

let me know if you think another method of implementing this would be better


TODO:
a way to pause the chess game and resume later

finish code for communicating moves between the two players

update current player message on GUI to be accurate


HOW TO RUN AND TEST:

clone the repository

open a terminal (not associtated with your IDE) in the 'src' folder

enter 'javac chess/ChessGUI.java'

enter 'java chess/ChessGUI.java'

select start new game 

input the port that this local process will be listening on

input the port that the other player's process will be listening on, they must be different and between 1024 and 65535

open another terminal window in the 'src' folder

enter 'java chess/ChessGUI.java'

select join existing game

For IP enter 'localhost'

enter the port number that this local process will be listening on. this must be the same as the second number you put in for the first player

enter the port number that the other player's game is listening on. this must be the same as the first number you put in for the first player

i know this is probably not the best way to do port numbers but it works and if i have time later i will improve it


