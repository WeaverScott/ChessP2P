# ChessP2P

Lauren: alright so the basic structure i thought we could do is have one client and one server class for each player. the server only listens for info while the client can only send it. we send a 4 character string to represent the moves, the first character is the row of the piece you are trying to move, the second character its column. the other two characters is the row column you are moving the piece to. this way we can just use the same method as the FTP server project. 

as far as getting the client/server to communicate with the prexisting chess game, i thought Chesspanel could instantiate the client and server, sending the instance of ChessModel to the server in the constructor. ChessPanel would communicate the local user's moves to the client class with a method.

let me know if you think another method of implementing this would be better


TODO:

code for moving pieces on chessboard after other player makes a move

get undo moves to reflect on both clients

get player label and currentPlayer to update on client that didn't make the move to disallow either client from moving both color pieces

on pawn reaching other side, get pawn change to reflect on other client

a way to pause the chess game and resume later

update current player message on GUI to be accurate

project report document: https://docs.google.com/document/d/1qPFpC5345-N-q53cAA-SwvuRChfkLaknbqNqsMb6gac/edit?usp=sharing

10 minute project demo video


HOW TO RUN AND TEST:

clone the repository

open a terminal (not associtated with your IDE) in the 'src' folder

enter 'javac chess/ChessGUI.java'

enter 'java chess/ChessGUI'

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


