#
default: compile
compile:
	javac -classpath .:/usr/local/Cellar/opencv/4.3.0_4/share/java/opencv4/opencv-430.jar image/Main.java

execute:
	java -Djava.library.path="/usr/local/Cellar/opencv/4.3.0_4/share/java/opencv4" -classpath .:/usr/local/Cellar/opencv/4.3.0_4/share/java/opencv4/opencv-430.jar: image.Main
