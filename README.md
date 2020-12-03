# ChessP2P

Lauren: alright so the basic structure i thought we could do is have one client and one server class for each player. the server only listens for info while the client can only send it. we send a 4 character string to represent the moves, the first character is the row of the piece you are trying to move, the second character its column. the other two characters is the row column you are moving the piece to. this way we can just use the same method as the FTP server project. 

as far as getting the client/server to communicate with the prexisting chess game, i thought Chesspanel could instantiate the client and server, sending the instance of ChessModel to the server in the constructor. ChessPanel would communicate the local user's moves to the client class with a method.

let me know if you think another method of implementing this would be better


TODO:
a way to pause the chess game and resume later

finish code for communicating moves between the two players

finish code for starting the connection between the two players
