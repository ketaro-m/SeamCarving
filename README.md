# SeamCarving
Image Resizing by [Seam Carving algorithm](https://en.wikipedia.org/wiki/Seam_carving).

## Description
Some non-square pictures don't fit SNS profile portraits, and they need to be resized.
**Seam Carving** technique resizes images preserving important features, unlike rescaling or cropping.  

<img width="400" alt="fig1.png" src="https://github.com/ketaro-m/SeamCarving/blob/master/fig/fig1.png"><img width="400" alt="fig3.png" src="https://github.com/ketaro-m/SeamCarving/blob/master/fig/fig3.png"> 

## Demo
Demo movie -> https://youtu.be/alv-BUySOPs

## Environment
- macOS Catalina 10.15.5
- [Java OpenJDK 13.0.2](https://www.oracle.com/java/technologies/javase-jdk13-downloads.html)
- [OpenCV](https://opencv.org/) 4.3.0

## Prerequisite
install Java OpenJDK 13.0.2

```zsh
# install Homebrew (if needed)
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
# update packages, install cask, and allow cask to look up versions
brew update
brew tap homebrew/cask
brew tap homebrew/cask-versions
# install Java
brew cask install java
```

## Usage
First, compile files with the following command
```
$ javac -classpath .:./opencv4/library/opencv-430.jar image/*.java
```
  
then, run the main file. You can input any aspect ratio (width:height).
```
$ java -Djava.library.path=./opencv4/library -classpath .:./opencv4/library/opencv-430.jar: image.Main [filename] [width] [height]
```

In the pop-up window titled "Drag regions you want to preserve.", you can manually select partial image areas to protect or destroy by dragging them. 

<img width="600" alt="fig4.png" src="https://github.com/ketaro-m/SeamCarving/blob/master/fig/fig4.png">

You can change your curcor size and protect/destroy mode by typing the following commands in the command line. (default; medium/protect)
|  Commands  |  Functions  |
| ---- | ---- |
|  s  |  small cursor size |
|  m  |  medium cursor size |
|  l  |  large cursor size |
|  +  |  protect mode  |
|  -  |  destroy mode  |

When you finish selecting protect/destroy regions, click Enter-key in the command line.  
Once the program has sucessfully run, the input image will be resized with the aspect ratio you entered.  

<img width="600" alt="fig2.png" src="https://github.com/ketaro-m/SeamCarving/blob/master/fig/fig2.png">  
(Top: Not selecting any region, Buttom: Selecting people to protect)  
<br />
You can save it from "File->Save" tab at the top of the image window.

## Demo mode

If you want to see how the algorithm works, checkout to the demo commit node and recompile files and rerun the program.

```
$ git checkout demo
$ javac -classpath .:./opencv4/library/opencv-430.jar image/*.java
$ java -Djava.library.path=./opencv4/library -classpath .:./opencv4/library/opencv-430.jar: image.Main [filename] [width] [height]
```
